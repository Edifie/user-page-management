package teste;


import junit.framework.TestCase;

public class Teste extends TestCase
{

	public String hello()
	{
		return hello2();
	}

	public String helloNull()
	{
		return null;
	}

	public String hello2()
	{
		return "Hello Student";
	}

	public void testHello()
	{
		assertEquals(hello2(),"Hello Student");

		//fail("FALHA PROPOSITADA");

		//helloNull().substring(0);

	}



	public static void main(String[] args)
	{
		System.out.println("OLA A TODOS");

	}
}