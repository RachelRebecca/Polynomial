import java.util.*;

public class Polynomial
{

    private GSLList<Monomial> poly;

    public Polynomial()
    {
        poly = new GSLList<>();
    }

    /**
     * addTerm term to the polynomial poly e.g. if poly is 1 + 2x then
     * addTerm(3x) should result in poly being 1 + 5x
     * If incoming term has a coefficient of zero, then it doesn't get inserted
     * (because it is zero or undefined if power is also zero)
     * @param term Monomial to be added
     */
    public void addTerm(Monomial term)
    {
        if (term != null)
        {
            if (poly.isEmpty() && term.getCoeff() != 0)
            {
                poly.insert(term);
            }
            else if (term.getCoeff() != 0)
            {
                boolean found = false;
                Iterator<Monomial> iterator = poly.iterator();
                while (iterator.hasNext())
                {
                    Monomial next = iterator.next();
                    if (next.compareTo(term) == 0)
                    {
                        found = true;
                        double newCoeff = term.getCoeff() + next.getCoeff();
                        if (newCoeff != 0)
                        {
                            next.setCoeff(newCoeff);
                        }
                        else
                        {
                            poly.remove(next);
                        }
                        break;
                    }
                }
                if (!found)
                {
                    poly.insert(term);
                }
            }
        }
    }

    /**
     * addPoly: add another polynomial to this one e.g. if our poly is 1 + 2x
     * and the other is 3 + x^2 then addPoly should transform our poly to 4 + 3x + x^2
     *
     * @param other Polynomial to add to this one
     */
    public void addPoly(Polynomial other)
    {
       if (other != null)
       {
            Iterator<Monomial> otherIterator = other.poly.iterator();
            while (otherIterator.hasNext())
            {
                addTerm(otherIterator.next());
            }
       }
    }


    /**
     * multiply this polynomial by other 
	 * for example if this is 1+2x+3x^ and other = 1 -3x^2, 
	 * then this should become 1+2x-6x^3-9x^4
     * @param other Polynomial to multiply this one by
     */
    public void multiplyBy(Polynomial other)
    {
        Polynomial[] terms = new Polynomial[other.poly.size()];

        Iterator<Monomial> otherIterator = other.poly.iterator();
        int index = 0;
        while (otherIterator.hasNext())
        {
            terms[index] = multiplyTerm(otherIterator.next());
            index++;
        }

        poly.head = null;
        for (int i=0; i<terms.length; i++)
        {
            addPoly(terms[i]);
        }
    }

    /**
     * Gets number of terms in Polynomial
     * @return int size of Polynomial GSLList
     */
    public int getSize()
    {
        return poly.size();
    }

    /**
     * multiplies the polynomial one monomial at a time
     * @param term: the monomial term to be multiplied with the polynomial
     * @return Polynomial: a newly created Polynomial object containing the result of the multiplication
     */
    private Polynomial multiplyTerm(Monomial term)
    {
        GSLList<Monomial> newOne = new GSLList<>();
        if (term != null)
        {
            if (!poly.isEmpty())
            {
                Iterator<Monomial> iterator = poly.iterator();
                while (iterator.hasNext())
                {
                    Monomial next = iterator.next();
                    double coefficient = term.getCoeff() * next.getCoeff();
                    int degree = term.getDegree() + next.getDegree();
                    newOne.insert(new Monomial(degree, coefficient));
                }
            }
        }
        Polynomial p = new Polynomial();
        p.poly = newOne;
        return p;
    }

    /**
     * toString method represents the sorted linked list of monomial terms as a string
     * following each term with a + except for the last, to create a Polynomial
     * @return the String representing the polynomial
     */
    public String toString()
    {
        String strRet = "";
        if (poly != null && !poly.isEmpty())
        {
            Iterator<Monomial> it = poly.iterator();
            while (it.hasNext())
            {
                Monomial term = it.next();
                strRet += term.toString();
                strRet += " + ";
            }
            strRet = strRet.substring(0, strRet.length() - 3); // eat last " + "
        }
        else
        {
            strRet = "0";
        }
        return strRet;
    }
}
