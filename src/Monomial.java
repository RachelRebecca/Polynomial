/**
 * Monomial class to be used as building blocks for polynomials
 */
public class Monomial implements Comparable<Object>
{

    private int degree;     // degree of the term.  Must be >= 0
    private double coeff;   // coefficient of the term
                            // 3.5x^2 would have degree = 2, and coeff = 3.5, etc

    public Monomial(int degree, double coeff)
    {
        if (degree < 0) // I ADDED THIS: if degree is less than zero, silently redirect
        {
            this.degree = 0;
            this.coeff = 0;
        }
        else
        {
            this.degree = degree;
            this.coeff = coeff;
        }
    }

    public Monomial(Monomial orig)
    {
        degree = orig.degree;
        coeff = orig.coeff;
    }

    //<editor-fold defaultstate="collapsed" desc="getters & setters">
    int getDegree()
    {
        return degree;
    }

    double getCoeff()
    {
        return coeff;
    }

    void setCoeff(double coeff)
    {
        this.coeff = coeff;
    }
    //</editor-fold>

    /**
     * compareTo : compare monomials strictly by their degrees <br>
     * thus, 2x^3 == 5x^3
     *
     * @param other Monomial to compare this one to
     * @return int - difference in degrees
     * @throws NullPointerException if other is null
     * @throws ClassCastException if other cannot be cast to Monomial
     */
    public int compareTo(Object other) throws NullPointerException, ClassCastException
    {
        if (other == null)
        {
            throw new NullPointerException();
        }
        if (other instanceof Monomial)
        {
            Monomial otherM = (Monomial) other;
            return degree - otherM.degree;
        }
        else
        {
            throw new ClassCastException();
        }
    }

    public boolean equals(Object other)
    {
        boolean areEqual = false;
        if (other instanceof Monomial)
        {
            Monomial otherM = (Monomial) other;
            areEqual = degree == otherM.getDegree();
        }
        return areEqual;
    }

    public String toString()
    {
        String strRet = "" + String.format("%.2f", coeff)
        + (degree == 0
               ? ""
               : degree == 1
                 ? "x"
                 : ("x^" + degree));
        return strRet;
    }
}
