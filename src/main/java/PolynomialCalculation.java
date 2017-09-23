
/**
 * given a and x, calculate y
 * y = a[n]*x^n + a[n-1]*x^(n-1) + ... + a[0]
 *
 * ANSWER:
 * y = a[n]
 * for (i=n-1; i>=0; i--) {
 *  y = x*y + a[i]
 * }
 *
 * */
public class PolynomialCalculation {
}
