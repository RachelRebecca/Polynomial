import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest
{

    public PolynomialTest() { }

    /**
     * Test basic cases of add term, some new terms have same degree as previous term, and others have different degree
     */
    @org.junit.jupiter.api.Test
    void testAddTerm()
    {
        System.out.println("Add term");
        //test basic adding terms (with same and different degrees)
        Polynomial p = new Polynomial();
        assertEquals("0", p.toString());
        p.addTerm(new Monomial(0, 1));
        assertEquals("1.00", p.toString());
        p.addTerm(new Monomial(0, 3));
        assertEquals("4.00", p.toString());
        p.addTerm(new Monomial(4, 6));
        assertEquals("4.00 + 6.00x^4", p.toString());
        p.addTerm(new Monomial(4, 5.4));
        assertEquals("4.00 + 11.40x^4", p.toString());
        p.addTerm(null);
        assertEquals("4.00 + 11.40x^4", p.toString());
        p.addTerm(new Monomial(3, -6));
        assertEquals("4.00 + -6.00x^3 + 11.40x^4", p.toString());
        p.addTerm(new Monomial(-4, -5.4)); // adding a decimal coefficient
        assertEquals("4.00 + -6.00x^3 + 11.40x^4", p.toString());
    }

    /**
     * Test add poly method, including cases where other Polynomial is empty or null, and basic cases
     * Also tests that commutative property for addition holds
     */
    @org.junit.jupiter.api.Test
    void testAddPoly()
    {
        System.out.println("Add poly");
        Polynomial p1 = new Polynomial();

        // test adding empty or null polynomials
        p1.addPoly(new Polynomial());
        assertEquals("0", p1.toString());
        p1.addPoly(null);
        assertEquals("0", p1.toString());

        // test basic addition (including negative numbers)
        Polynomial p2 = new Polynomial();
        for (int i = 0; i < 10; i++)
        {
            p1.addTerm(new Monomial(i, i + 1));
        }

        assertEquals("1.00 + 2.00x + 3.00x^2 + 4.00x^3 + 5.00x^4 + 6.00x^5 + 7.00x^6 + 8.00x^7 + 9.00x^8 + " +
                "10.00x^9", p1.toString());
        for (int i = -10; i < 10; i++)
        {
            p2.addTerm(new Monomial(i + 10, i));
        }
        assertEquals("-10.00 + -9.00x + -8.00x^2 + -7.00x^3 + -6.00x^4 + -5.00x^5 + -4.00x^6 + -3.00x^7 + " +
                "-2.00x^8 " + "+ -1.00x^9 + 1.00x^11 + 2.00x^12 + 3.00x^13 + 4.00x^14 + 5.00x^15 + 6.00x^16 + 7.00x^17 +" +
                " 8.00x^18 + " + "9.00x^19", p2.toString());

        p1.addPoly(p2);
        assertEquals("-9.00 + -7.00x + -5.00x^2 + -3.00x^3 + -1.00x^4 + 1.00x^5 + 3.00x^6 + 5.00x^7 + " +
                "7.00x^8 + 9.00x^9 + 1.00x^11 + 2.00x^12 + 3.00x^13 + 4.00x^14 + 5.00x^15 + 6.00x^16 + 7.00x^17 " +
                "+ 8.00x^18 + 9.00x^19", p1.toString());

        // test commutative property
        Polynomial p3 = new Polynomial();
        p3.addTerm(new Monomial(2, 5));
        p3.addTerm(new Monomial(3, 100));
        Polynomial p4 = new Polynomial();
        p4.addTerm(new Monomial(3, 6));
        p4.addTerm(new Monomial(1, 3));
        Polynomial tempP3 = new Polynomial();
        tempP3.addTerm(new Monomial(2, 5));
        tempP3.addTerm(new Monomial(3, 100));
        p3.addPoly(p4);
        p4.addPoly(tempP3);
        assertEquals(p3.toString(), p4.toString()); // commutative property
    }

    /**
     * Tests multiply by method, including multiplying by zero and basic cases
     * Also tests that commutative property for multiplication holds
     */
    @org.junit.jupiter.api.Test
    void testMultiplyBy()
    {
        System.out.println("Multiply by");
        Polynomial p1 = new Polynomial();

        // test multiply by zero
        p1.addTerm(new Monomial(2, 2));
        p1.addTerm(new Monomial(3, 4));
        Polynomial p2 = new Polynomial();
        p2.addTerm(new Monomial(-1, 0));
        p1.multiplyBy(p2);
        assertEquals("0", p1.toString());

        // test basic multiplication, including three-term multiplication and negative numbers
        p1.addTerm(new Monomial(2, 2));
        p2.addTerm(new Monomial(3, 4));
        p2.addTerm(new Monomial(2, 4));
        p1.multiplyBy(p2);
        assertEquals("8.00x^4 + 8.00x^5", p1.toString());
        Polynomial p3 = new Polynomial();
        p3.addTerm(new Monomial(9, -1));
        p3.addTerm(new Monomial(4, 3));
        p1.multiplyBy(p3);
        assertEquals("24.00x^8 + 24.00x^9 + -8.00x^13 + -8.00x^14", p1.toString());
        p3.addTerm(new Monomial(6, 3));
        p1.multiplyBy(p3); // three-term multiplication, and negative number multiplication
        assertEquals("72.00x^12 + 72.00x^13 + 72.00x^14 + 72.00x^15 + -48.00x^17 + -48.00x^18 + -24.00x^19 +" +
                " -24.00x^20 + 8.00x^22 + 8.00x^23", p1.toString());

        // test multiplying by decimals and commutative property:
        Polynomial p4 = new Polynomial();
        p4.addTerm(new Monomial(5, 2.75));
        Polynomial p5= new Polynomial();
        p5.addTerm(new Monomial(4, 2.68));
        p5.addTerm(new Monomial(5, 10.2));
        Polynomial tempP4 = new Polynomial();
        tempP4.addTerm(new Monomial(5, 2.75));
        p4.multiplyBy(p5); //decimal multiplication
        assertEquals("7.37x^9 + 28.05x^10", p4.toString());
        p5.multiplyBy(tempP4);
        assertEquals("7.37x^9 + 28.05x^10", p5.toString());
        assertEquals(p4.toString(), p5.toString()); //commutative property
    }

    /**
     * Tests get size method
     */
    @org.junit.jupiter.api.Test
    void testGetSize()
    {
        System.out.println("Get size");
        Polynomial p1 = new Polynomial();
        p1.addPoly(new Polynomial());
        assertEquals(0, p1.getSize());
        p1.addPoly(null);
        assertEquals(0, p1.getSize());
        p1.addTerm(null);
        assertEquals(0, p1.getSize());
        for (int i = 10; i < 30; i += 10)
        {
            p1.addTerm(new Monomial(i, i));
            assertEquals(i/10, p1.getSize());
        }
    }
}