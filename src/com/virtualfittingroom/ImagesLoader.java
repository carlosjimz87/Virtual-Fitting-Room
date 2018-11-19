package com.virtualfittingroom;


public class ImagesLoader {
	public static ProductItem[] items = null; 	

	public static ProductItem[] getArray(){
		
	return items;	
	}
	
	public static void LoadImages(){
    items = new ProductItem[128]; 

    //DOLCE GABBANA
    items[0]=new ProductItem("Male","DolceGabbana","shirts","XL","T",R.drawable.m_dg_shirts_xl);
    items[1]=new ProductItem("Male","DolceGabbana","shirts","L","T",R.drawable.m_dg_shirts_l);
    items[2]=new ProductItem("Male","DolceGabbana","shirts","M","T",R.drawable.m_dg_shirts_m);
    items[3]=new ProductItem("Male","DolceGabbana","shirts","S","T",R.drawable.m_dg_shirts_s); 

    items[4]=new ProductItem("Male","DolceGabbana","full","XL","T",R.drawable.m_dg_suits_xl);
    items[5]=new ProductItem("Male","DolceGabbana","full","L","T",R.drawable.m_dg_suits_l);
    items[6]=new ProductItem("Male","DolceGabbana","full","M","T",R.drawable.m_dg_suits_m);
    items[7]=new ProductItem("Male","DolceGabbana","full","S","T",R.drawable.m_dg_suits_s);

    items[8]=new ProductItem("Male","DolceGabbana","shorts","XL","B",R.drawable.m_dg_shorts_xl);
    items[9]=new ProductItem("Male","DolceGabbana","shorts","L","B",R.drawable.m_dg_shorts_l);
    items[10]=new ProductItem("Male","DolceGabbana","shorts","M","B",R.drawable.m_dg_shorts_m);
    items[11]=new ProductItem("Male","DolceGabbana","shorts","S","B",R.drawable.m_dg_shorts_s);   
    
    items[12]=new ProductItem("Male","DolceGabbana","pants","XL","B",R.drawable.m_dg_pants_xl);
    items[13]=new ProductItem("Male","DolceGabbana","pants","L","B",R.drawable.m_dg_pants_l);
    items[14]=new ProductItem("Male","DolceGabbana","pants","M","B",R.drawable.m_dg_pants_m);
    items[15]=new ProductItem("Male","DolceGabbana","pants","S","B",R.drawable.m_dg_pants_s);
    
    items[16]=new ProductItem("Female","DolceGabbana","shirts","XL","T",R.drawable.w_dg_shirts_xl);
    items[17]=new ProductItem("Female","DolceGabbana","shirts","L","T",R.drawable.w_dg_shirts_l);
    items[18]=new ProductItem("Female","DolceGabbana","shirts","M","T",R.drawable.w_dg_shirts_m); 
    items[19]=new ProductItem("Female","DolceGabbana","shirts","S","T",R.drawable.w_dg_shirts_s);

    items[20]=new ProductItem("Female","DolceGabbana","full","XL","T",R.drawable.w_dg_dress_xl);
    items[21]=new ProductItem("Female","DolceGabbana","full","L","T",R.drawable.w_dg_dress_l);
    items[22]=new ProductItem("Female","DolceGabbana","full","M","T",R.drawable.w_dg_dress_m);
    items[23]=new ProductItem("Female","DolceGabbana","full","S","T",R.drawable.w_dg_dress_s);

    items[24]=new ProductItem("Female","DolceGabbana","shorts","XL","B",R.drawable.w_dg_shorts_xl);
    items[25]=new ProductItem("Female","DolceGabbana","shorts","L","B",R.drawable.w_dg_shorts_l);
    items[26]=new ProductItem("Female","DolceGabbana","shorts","M","B",R.drawable.w_dg_shorts_m);
    items[27]=new ProductItem("Female","DolceGabbana","shorts","S","B",R.drawable.w_dg_shorts_s);   
    
    items[28]=new ProductItem("Female","DolceGabbana","pants","XL","B",R.drawable.w_dg_pants_xl);
    items[29]=new ProductItem("Female","DolceGabbana","pants","L","B",R.drawable.w_dg_pants_l);
    items[30]=new ProductItem("Female","DolceGabbana","pants","M","B",R.drawable.w_dg_pants_m);
    items[31]=new ProductItem("Female","DolceGabbana","pants","S","B",R.drawable.w_dg_pants_s); 


    //CALVIN KLEIN
    items[32]=new ProductItem("Male","CalvinKlein","shirts","XL","T",R.drawable.m_ck_shirts_xl);
    items[33]=new ProductItem("Male","CalvinKlein","shirts","L","T",R.drawable.m_ck_shirts_l);
    items[34]=new ProductItem("Male","CalvinKlein","shirts","M","T",R.drawable.m_ck_shirts_m);
    items[35]=new ProductItem("Male","CalvinKlein","shirts","S","T",R.drawable.m_ck_shirts_s); 

    items[36]=new ProductItem("Male","CalvinKlein","full","XL","T",R.drawable.m_ck_suits_xl);
    items[37]=new ProductItem("Male","CalvinKlein","full","L","T",R.drawable.m_ck_suits_l);
    items[38]=new ProductItem("Male","CalvinKlein","full","M","T",R.drawable.m_ck_suits_m);
    items[39]=new ProductItem("Male","CalvinKlein","full","S","T",R.drawable.m_ck_suits_s);

    items[40]=new ProductItem("Male","CalvinKlein","shorts","XL","B",R.drawable.m_ck_shorts_xl);
    items[41]=new ProductItem("Male","CalvinKlein","shorts","L","B",R.drawable.m_ck_shorts_l);
    items[42]=new ProductItem("Male","CalvinKlein","shorts","M","B",R.drawable.m_ck_shorts_m);
    items[43]=new ProductItem("Male","CalvinKlein","shorts","S","B",R.drawable.m_ck_shorts_s);   
    
    items[44]=new ProductItem("Male","CalvinKlein","pants","XL","B",R.drawable.m_ck_pants_xl);
    items[45]=new ProductItem("Male","CalvinKlein","pants","L","B",R.drawable.m_ck_pants_l);
    items[46]=new ProductItem("Male","CalvinKlein","pants","M","B",R.drawable.m_ck_pants_m);
    items[47]=new ProductItem("Male","CalvinKlein","pants","S","B",R.drawable.m_ck_pants_s);
    
    items[48]=new ProductItem("Female","CalvinKlein","shirts","XL","T",R.drawable.w_ck_shirts_xl);
    items[49]=new ProductItem("Female","CalvinKlein","shirts","L","T",R.drawable.w_ck_shirts_l);
    items[50]=new ProductItem("Female","CalvinKlein","shirts","M","T",R.drawable.w_ck_shirts_m); 
    items[51]=new ProductItem("Female","CalvinKlein","shirts","S","T",R.drawable.w_ck_shirts_s);

    items[52]=new ProductItem("Female","CalvinKlein","full","XL","T",R.drawable.w_ck_dress_xl);
    items[53]=new ProductItem("Female","CalvinKlein","full","L","T",R.drawable.w_ck_dress_l);
    items[54]=new ProductItem("Female","CalvinKlein","full","M","T",R.drawable.w_ck_dress_m);
    items[55]=new ProductItem("Female","CalvinKlein","full","S","T",R.drawable.w_ck_dress_s);

    items[56]=new ProductItem("Female","CalvinKlein","shorts","XL","B",R.drawable.w_ck_shorts_xl);
    items[57]=new ProductItem("Female","CalvinKlein","shorts","L","B",R.drawable.w_ck_shorts_l);
    items[58]=new ProductItem("Female","CalvinKlein","shorts","M","B",R.drawable.w_ck_shorts_m);
    items[59]=new ProductItem("Female","CalvinKlein","shorts","S","B",R.drawable.w_ck_shorts_s);   
    
    items[60]=new ProductItem("Female","CalvinKlein","pants","XL","B",R.drawable.w_ck_pants_xl);
    items[61]=new ProductItem("Female","CalvinKlein","pants","L","B",R.drawable.w_ck_pants_l);
    items[62]=new ProductItem("Female","CalvinKlein","pants","M","B",R.drawable.w_ck_pants_m);
    items[63]=new ProductItem("Female","CalvinKlein","pants","S","B",R.drawable.w_ck_pants_s); 
    

    //MANGO
    items[64]=new ProductItem("Male","Mango","shirts","XL","T",R.drawable.m_mango_shirts_xl);
    items[65]=new ProductItem("Male","Mango","shirts","L","T",R.drawable.m_mango_shirts_l);
    items[66]=new ProductItem("Male","Mango","shirts","M","T",R.drawable.m_mango_shirts_m);
    items[67]=new ProductItem("Male","Mango","shirts","S","T",R.drawable.m_mango_shirts_s); 

    items[68]=new ProductItem("Male","Mango","full","XL","T",R.drawable.m_mango_suits_xl);
    items[69]=new ProductItem("Male","Mango","full","L","T",R.drawable.m_mango_suits_l);
    items[70]=new ProductItem("Male","Mango","full","M","T",R.drawable.m_mango_suits_m);
    items[71]=new ProductItem("Male","Mango","full","S","T",R.drawable.m_mango_suits_s);

    items[72]=new ProductItem("Male","Mango","shorts","XL","B",R.drawable.m_mango_shorts_xl);
    items[73]=new ProductItem("Male","Mango","shorts","L","B",R.drawable.m_mango_shorts_l);
    items[74]=new ProductItem("Male","Mango","shorts","M","B",R.drawable.m_mango_shorts_m);
    items[75]=new ProductItem("Male","Mango","shorts","S","B",R.drawable.m_mango_shorts_s);   
    
    items[76]=new ProductItem("Male","Mango","pants","XL","B",R.drawable.m_mango_pants_xl);
    items[77]=new ProductItem("Male","Mango","pants","L","B",R.drawable.m_mango_pants_l);
    items[78]=new ProductItem("Male","Mango","pants","M","B",R.drawable.m_mango_pants_m);
    items[79]=new ProductItem("Male","Mango","pants","S","B",R.drawable.m_mango_pants_s);
    
    items[80]=new ProductItem("Female","Mango","shirts","XL","T",R.drawable.w_mango_shirts_xl);
    items[81]=new ProductItem("Female","Mango","shirts","L","T",R.drawable.w_mango_shirts_l);
    items[82]=new ProductItem("Female","Mango","shirts","M","T",R.drawable.w_mango_shirts_m); 
    items[83]=new ProductItem("Female","Mango","shirts","S","T",R.drawable.w_mango_shirts_s);

    items[84]=new ProductItem("Female","Mango","full","XL","T",R.drawable.w_mango_dress_xl);
    items[85]=new ProductItem("Female","Mango","full","L","T",R.drawable.w_mango_dress_l);
    items[86]=new ProductItem("Female","Mango","full","M","T",R.drawable.w_mango_dress_m);
    items[87]=new ProductItem("Female","Mango","full","S","T",R.drawable.w_mango_dress_s);

    items[88]=new ProductItem("Female","Mango","shorts","XL","B",R.drawable.w_mango_shorts_xl);
    items[89]=new ProductItem("Female","Mango","shorts","L","B",R.drawable.w_mango_shorts_l);
    items[90]=new ProductItem("Female","Mango","shorts","M","B",R.drawable.w_mango_shorts_m);
    items[91]=new ProductItem("Female","Mango","shorts","S","B",R.drawable.w_mango_shorts_s);   
    
    items[92]=new ProductItem("Female","Mango","pants","XL","B",R.drawable.w_mango_pants_xl);
    items[93]=new ProductItem("Female","Mango","pants","L","B",R.drawable.w_mango_pants_l);
    items[94]=new ProductItem("Female","Mango","pants","M","B",R.drawable.w_mango_pants_m);
    items[95]=new ProductItem("Female","Mango","pants","S","B",R.drawable.w_mango_pants_s); 
   

    //ZARA
    items[96]=new ProductItem("Male","Zara","shirts","XL","T",R.drawable.m_zara_shirts_xl);
    items[97]=new ProductItem("Male","Zara","shirts","L","T",R.drawable.m_zara_shirts_l);
    items[98]=new ProductItem("Male","Zara","shirts","M","T",R.drawable.m_zara_shirts_m);
    items[99]=new ProductItem("Male","Zara","shirts","S","T",R.drawable.m_zara_shirts_s); 

    items[100]=new ProductItem("Male","Zara","full","XL","T",R.drawable.m_zara_suits_xl);
    items[101]=new ProductItem("Male","Zara","full","L","T",R.drawable.m_zara_suits_l);
    items[102]=new ProductItem("Male","Zara","full","M","T",R.drawable.m_zara_suits_m);
    items[103]=new ProductItem("Male","Zara","full","S","T",R.drawable.m_zara_suits_s);

    items[104]=new ProductItem("Male","Zara","shorts","XL","B",R.drawable.m_zara_shorts_xl);
    items[105]=new ProductItem("Male","Zara","shorts","L","B",R.drawable.m_zara_shorts_l);
    items[106]=new ProductItem("Male","Zara","shorts","M","B",R.drawable.m_zara_shorts_m);
    items[107]=new ProductItem("Male","Zara","shorts","S","B",R.drawable.m_zara_shorts_s);   
    
    items[108]=new ProductItem("Male","Zara","pants","XL","B",R.drawable.m_zara_pants_xl);
    items[109]=new ProductItem("Male","Zara","pants","L","B",R.drawable.m_zara_pants_l);
    items[110]=new ProductItem("Male","Zara","pants","M","B",R.drawable.m_zara_pants_m);
    items[111]=new ProductItem("Male","Zara","pants","S","B",R.drawable.m_zara_pants_s);
    
    items[112]=new ProductItem("Female","Zara","shirts","XL","T",R.drawable.w_zara_shirts_xl);
    items[113]=new ProductItem("Female","Zara","shirts","L","T",R.drawable.w_zara_shirts_l);
    items[114]=new ProductItem("Female","Zara","shirts","M","T",R.drawable.w_zara_shirts_m); 
    items[115]=new ProductItem("Female","Zara","shirts","S","T",R.drawable.w_zara_shirts_s);

    items[116]=new ProductItem("Female","Zara","full","XL","T",R.drawable.w_zara_dress_xl);
    items[117]=new ProductItem("Female","Zara","full","L","T",R.drawable.w_zara_dress_l);
    items[118]=new ProductItem("Female","Zara","full","M","T",R.drawable.w_zara_dress_m);
    items[119]=new ProductItem("Female","Zara","full","S","T",R.drawable.w_zara_dress_s);

    items[120]=new ProductItem("Female","Zara","shorts","XL","B",R.drawable.w_zara_shorts_xl);
    items[121]=new ProductItem("Female","Zara","shorts","L","B",R.drawable.w_zara_shorts_l);
    items[122]=new ProductItem("Female","Zara","shorts","M","B",R.drawable.w_zara_shorts_m);
    items[123]=new ProductItem("Female","Zara","shorts","S","B",R.drawable.w_zara_shorts_s);   
    
    items[124]=new ProductItem("Female","Zara","pants","XL","B",R.drawable.w_zara_pants_xl);
    items[125]=new ProductItem("Female","Zara","pants","L","B",R.drawable.w_zara_pants_l);
    items[126]=new ProductItem("Female","Zara","pants","M","B",R.drawable.w_zara_pants_m);
    items[127]=new ProductItem("Female","Zara","pants","S","B",R.drawable.w_zara_pants_s); 
	}
}
