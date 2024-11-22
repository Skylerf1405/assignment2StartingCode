/**
 * @author Group Sonia: Skyler Fuller, Parsa J
 * @version 1.0 Nov. 22, 2024   
 * Class Description:
 * XML Parser implementation using Stack and Queue ADTs.
 */

package appDomain;

import implementations.MyStack;
import implementations.MyQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppDomain {
    private static MyStack<String> stack;
    private static MyQueue<String> errorQ;
    private static MyQueue<String> extrasQ;
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java AppDomain <xml-file>");
            return;
        }
        
        parseXMLFile(args[0]);
    }
    
    private static void parseXMLFile(String filename) {
        stack = new MyStack<>();
        errorQ = new MyQueue<>();
        extrasQ = new MyQueue<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
            
            while (!stack.isEmpty()) {
                errorQ.enqueue(stack.pop());
            }
            
            reportErrors();
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static void processLine(String line) {
        Pattern tagPattern = Pattern.compile("</?[^>]+/?>");
        Matcher matcher = tagPattern.matcher(line);
        
        while (matcher.find()) {
            String tag = matcher.group();
            
            if (tag.startsWith("<?") && tag.endsWith("?>")) {
                continue;
            }
            
            // Self-closing tag
            if (tag.endsWith("/>")) {
                continue;
            }
            
            // end tag
            if (tag.startsWith("</")) {
                String tagName = tag.substring(2, tag.length() - 1);
                processEndTag(tagName);
            }
            // Start tag
            else if (tag.startsWith("<")) {
                String tagName = tag.substring(1, tag.length() - 1);
                if (tagName.contains(" ")) {
                    tagName = tagName.substring(0, tagName.indexOf(" "));
                }
                stack.push(tagName);
            }
        }
    }
    
    private static void processEndTag(String tagName) {
        if (stack.isEmpty()) {
            extrasQ.enqueue(tagName);
            return;
        }

        String top = stack.peek();
        if (tagName.equals(top)) {
            stack.pop();
            return;
        }

        // Search stack for matching tag
        MyStack<String> tempStack = new MyStack<>();
        boolean found = false;
        
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals(tagName)) {
                found = true;
                break;
            }
            errorQ.enqueue(current);
            tempStack.push(current);
        }
        
        // If no match found- closing tag
        if (!found) {
            extrasQ.enqueue(tagName);
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }
    }
    
    private static void reportErrors() {
        boolean hasErrors = false;
        
        if (stack.size() > 1) {
            System.out.println("Error: Multiple root elements found");
            hasErrors = true;
        }
        
        while (!errorQ.isEmpty()) {
            hasErrors = true;
            System.out.println("Error: Unclosed tag <" + errorQ.dequeue() + ">");
        }
        
        while (!extrasQ.isEmpty()) {
            hasErrors = true;
            System.out.println("Error: Unmatched closing tag </" + extrasQ.dequeue() + ">");
        }
        
        if (!hasErrors) {
            System.out.println("XML document is well-formed.");
        }
    }
}
