package String;

/**
 * Check for balanced parentheses in an expression
 * true for exp = “[()]{}{[()()]()}” and false for exp = “[(])”
 *
 * Algorithm:
 1) Declare a character stack S.
 2) Now traverse the expression string exp.
 a) If the current character is a starting bracket (‘(‘ or ‘{‘ or ‘[‘) then push it to stack.
 b) If the current character is a closing bracket (‘)’ or ‘}’ or ‘]’) then pop from stack and if the popped character is the matching starting bracket then fine else parenthesis are not balanced.
 3) After complete traversal, if there is some starting bracket left in stack then “not balanced”
 * */
public class CheckBalancedParentheses {
}
