import static org.junit.Assert.*;

import org.junit.Test;

public class VolatileDataTest {

	@Test
	public void setLength1()
	{
		// Contract: increase the probability of that setting and getting LENGTH1 works
		VolatileData d = new VolatileData();

		d.setLength1(3.15);
		assertEquals(3.15, d.getLength1(), 0);
	}

	@Test
	public void setRadius1()
	{
		// Contract: increase the probability of that setting and getting RADIUS1 works
		VolatileData d = new VolatileData();

		d.setRadius1(3.14);
		assertEquals(3.14, d.getRadius1(), 0);
	}

	@Test
	public void setEpsilon()
	{
		// Contract: increase the probability of that setting and getting EPSILON works
		VolatileData d = new VolatileData();

		d.setEpsilon(3.14);
		assertEquals(3.14, d.getEpsilon(), 0);
	}

	@Test
	public void setArea1()
	{
		// Contract: increase the probability of that setting and getting AREA1 works
		VolatileData d = new VolatileData();

		d.setArea1(3.16);
		assertEquals(3.16, d.getArea1(), 0);
	}

	@Test
	public void setLength2()
	{
		// Contract: increase the probability of that setting and getting LENGTH2 works
		VolatileData d = new VolatileData();

		d.setLength2(3.17);
		assertEquals(3.17, d.getLength2(), 0);
	}

	@Test
	public void setRadius2()
	{
		// Contract: increase the probability of that setting and getting RADIUS2 works
		VolatileData d = new VolatileData();

		d.setRadius2(3.18);
		assertEquals(3.18, d.getRadius2(), 0);
	}

	@Test
	public void setArea2()
	{
		// Contract: increase the probability of that setting and getting AREA2 works
		VolatileData d = new VolatileData();

		d.setArea2(3.19);
		assertEquals(3.19, d.getArea2(), 0);
	}

}
