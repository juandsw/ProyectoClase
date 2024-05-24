package co.edu.uco.pch.crosscutting.helpers;

import java.util.Random;
import java.util.UUID;

public class UUIDHelper {
	
	private static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000" ;
	
	public static final UUID convertToUUID(final String uuidAsString) {
		return UUID.fromString(uuidAsString);
		}
	public static final UUID getDafault(final UUID value, final UUID defaultValue) {
		return ObjectHelper.getObjectHelper().getDefaultValue(value, defaultValue);
	}
	
	public static final UUID getDefault() {
		return convertToUUID(DEFAULT_UUID_STRING);
	}
	
	public static final UUID generate() {
		
		Random random = new Random();
        long mas = random.nextLong();
        long menos = random.nextLong();
        return new UUID(mas, menos);
		
		
		
	}
	private UUIDHelper() {
		super();
	}
	
public static void main(String[] args) {
	System.out.println(UUID.randomUUID());
}

}
