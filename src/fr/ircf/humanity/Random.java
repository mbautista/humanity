package fr.ircf.humanity;

public class Random {

	private static java.util.Random random = new java.util.Random();
	
	public static double nextDouble(){
		return random.nextDouble();
	}
	
	public static int nextInt(int i){
		return random.nextInt(i);
	}
	
	public static double between(double min, double max){
		return min + random.nextDouble() * (max - min);
	}
	
	public static double gaussian(double max){
		return max * random.nextGaussian();
	}
	
	public static double betweenWithFactor(double min, double max, double factor){
		return min + random.nextDouble() * (max-min) * factor;
	}
	
	public static float[] colorBetweenIntensity(double min, double max){
		float[] c = new float[3];
		c[0] = random.nextFloat();
		c[1] = random.nextFloat();
		c[2] = random.nextFloat();
		float i = (float) (3 * (min + random.nextDouble() * (max-min)) / (c[0] + c[1] + c[2]));
		c[0]*= i;
		c[1]*= i;
		c[2]*= i;
		return c;
	}
	
	private static int MIN_SYLLABUS = 1, MAX_SYLLABUS = 3;
	private static String VOWELS = "aeiouy";
	private static String CONSONANTS = "bcdfghjklmnpqrstvwxz";
	public static String name(){
		int length = MIN_SYLLABUS + random.nextInt(MAX_SYLLABUS);
		String name = "";
		for (int i=0; i<length; i++) name+= syllabus();
		return name;
	}
	
	private static String syllabus(){
		String syllabus = "" + _char();
		syllabus += _char(!VOWELS.contains(syllabus));
		return syllabus;
	}

	private static char _char(){
		return (char)(97 + random.nextInt(26));
	}
	
	private static char _char(boolean vowel){
		if (vowel){
			return VOWELS.charAt(random.nextInt(VOWELS.length()));
		}else{
			return CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
		}
	}
	
}
