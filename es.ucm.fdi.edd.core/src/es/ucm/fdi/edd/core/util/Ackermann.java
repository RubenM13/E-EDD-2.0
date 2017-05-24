package es.ucm.fdi.edd.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Ackermann function
 * <p>
 * The Ackermann function is a classic example of a recursive function, notable
 * especially because it is not a primitive recursive function. It grows very
 * quickly in value, as does the size of its call tree.
 * <p>
 * <p>
 * {@link http://rosettacode.org/wiki/Ackermann_function}
 * </p>
 * <p>
 * To avoid StackOverflowError, you can increase the thread stack size using the
 * -Xss flag. -Xss<size>[g|G|m|M|k|K]
 * <p>
 * <p>Example: java -Xss4m Ackermann</p>
 * 
 * @author Joel
 */
public class Ackermann {

	private static final String COMMA = ",";

	public static BigInteger ack(BigInteger m, BigInteger n) {
		return m.equals(BigInteger.ZERO) ? n.add(BigInteger.ONE) : ack(
				m.subtract(BigInteger.ONE),
				n.equals(BigInteger.ZERO) ? BigInteger.ONE : ack(m,
						n.subtract(BigInteger.ONE)));
	}

	public static long ackermann(long m, long n) {
		if (m == 0)
			return n + 1;
		if (n == 0)
			return ackermann(m - 1, 1);
		return ackermann(m - 1, ackermann(m, n - 1));
	}

	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			while (true) {
				System.out.println("Ackermann function: | [Q]uit");
				String action = in.readLine();
				if ((action.length() == 0)
						|| action.toUpperCase().charAt(0) == 'Q'
						|| action.toUpperCase().charAt(0) == 'q') {
					break;
				}

				if (action.contains(COMMA)) {
					String[] parts = action.split(COMMA);
					if (parts.length == 2) {
						String part1 = parts[0];
						String part2 = parts[1];
						// BigInteger ack = ack(new BigInteger(part1), new
						// BigInteger(part2));
						// System.out.println(String.format("ack(%s,%s) = %s",
						// part1, part2, ack));
						long M = Long.parseLong(part1);
						long N = Long.parseLong(part2);
						System.out.println(String.format("ack(%s,%s) = %s",
								part1, part2, ackermann(M, N)));
					} else {
						System.out
								.println("Introduce a valid expression! Format: #,# -> Example: 3,3");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (StackOverflowError e) {
	        System.err.println("reported recursion level was: " + e.getStackTrace().length);
	    }
	}
}
