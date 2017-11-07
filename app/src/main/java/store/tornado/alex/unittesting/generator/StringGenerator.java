package store.tornado.alex.unittesting.generator;

/**
 * Created by: anna
 * Date: 11/5/17.
 */

public class StringGenerator implements Generator {
	@Override
	public String generate(String prefix) {
		return String.format("%s - String result", prefix);
	}
}
