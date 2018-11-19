package com.virtualfittingroom;

public class MeasureAdjuster {
	
	@SuppressWarnings("unused")
	private static String head="";
	@SuppressWarnings("unused")
	private static String shoulders="";
	@SuppressWarnings("unused")
	private static String chest="";
	private static String waist="";
	@SuppressWarnings("unused")
	private static String neck="";
	@SuppressWarnings("unused")
	private static String height="";
	@SuppressWarnings("unused")
	private static String hips="";
	@SuppressWarnings("unused")
	private static String buts="";
	@SuppressWarnings("unused")
	private static String thighs="";
	@SuppressWarnings("unused")
	private static String calves="";
	public static String upperSize="";
	public static String lowerSize="";
	
	public static void setMeasures(String measure){
		measure=measure.substring(1);
		String [] measuresArray= measure.split("\\*");
		for(int i =0;i<measuresArray.length;i++){
			String[] keyvaluePar=measuresArray[i].split(" ");
			if(keyvaluePar[0].equals("M01"))
				head=keyvaluePar[1];
			if(keyvaluePar[0].equals("M02"))
				shoulders=keyvaluePar[1];
			if(keyvaluePar[0].equals("M03"))
				chest=keyvaluePar[1];
			if(keyvaluePar[0].equals("M04"))
				waist=keyvaluePar[1];
			if(keyvaluePar[0].equals("M05"))
				neck=keyvaluePar[1];
			if(keyvaluePar[0].equals("M06"))
				height=keyvaluePar[1];
			if(keyvaluePar[0].equals("M07"))
				hips=keyvaluePar[1];
			if(keyvaluePar[0].equals("M08"))
				buts=keyvaluePar[1];
			if(keyvaluePar[0].equals("M09"))
				thighs=keyvaluePar[1];
			if(keyvaluePar[0].equals("M11"))
				upperSize=keyvaluePar[1];
				
		}
				
		//upperSize=getUpperMeasure(Float.parseFloat(chest));
		lowerSize=getLowerMeasure(Float.parseFloat(waist));
	}

	@SuppressWarnings("unused")
	private static String getUpperMeasure(float chest){
		float chestInchs=(float) (chest/2.54);
		String size="";
		if(chestInchs>=35 && chestInchs<=37)
			size="S";
		else if(chestInchs>37 && chestInchs<=40)
			size="M";
		else if(chestInchs>40 && chestInchs<=42)
			size="L";
		else if(chestInchs>42 && chestInchs<=48)
			size="XL";
		else if(chestInchs>48 && chestInchs<=52)
			size="XXL";
		else if(chestInchs>52)
			size="XXXL";
		else if(chestInchs<35)
			size="XS";
		
		return size;
		
	}
	
	private static String getLowerMeasure(float waist){
		float waistIchs=(float) (waist/2.54);
		String size="";
		if(waistIchs>=29 && waistIchs<=31)
			size="S";
		else if(waistIchs>31 && waistIchs<=34)
			size="M";
		else if(waistIchs>34 && waistIchs<=38)
			size="L";
		else if(waistIchs>38 && waistIchs<=42)
			size="XL";
		else if(waistIchs>42 && waistIchs<=46)
			size="XXL";
		else if(waistIchs>=46)
			size="XXXL";
		else if(waistIchs<29)
			size="XS";
		
		return size;
		
	}
}
