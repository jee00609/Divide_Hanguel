import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HunMinChange {
	
	/*자바 이클립스는 유니코드가 기본이다.*/
	
	// (초성)ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ 
    private static final char[] CHO = 
    {0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 
        0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 
        0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e};
     
    // (중성)ㅏㅐㅑㅒㅓㅔㅕㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ
    private static final char[] JUN = 
    {0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 
        0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 
        0x315c, 0x315d, 0x315e, 0x315f, 0x3160,    0x3161,    
        0x3162, 0x3163};
 
    // (종성)ㄱㄲㄳㄴㄵㄶㄷㄹㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ
    // ㄽ 은 현재 사용하지 않는것 같다 --> 돐잔치(X) 돌잔치(O) 북한에서는 돐잔치라고 하는 것 같다.
    private static final char[] JON = 
    {0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 
        0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 
        0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 
        0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 
        0x314c, 0x314d, 0x314e};
    
    //생성자 파트
    public HunMinChange(String tempStr,String lastStr) {//메인함수에서 입력받아온다.
    	
    	/*List list = new ArrayList(); 와 ArrayList list = new ArrayList(); 의 차이는 무엇인가.
		클래스를 생성할 때 도형 타입으로 생성하게 되면 정사각형이 아닌 다른 직사각형, 삼각형 등 도형 인터페이스를 구현한
		클래스에서 사용 될 수 있다. 하지만 정사각형 클래스로 생성하게 되면 직사각형, 삼각형 등 에서는 사용할 수 없다.

		List list = new ArrayList();
		-> 도형 list = new 정사각형();
		
		ArrayList list = new ArrayList();
		-> 정사각형 list = new 정사각형();
		(List는 interface다. interface는 공통되는 메소드를 추출해 놓은 클래스로 생각하면 된다.)*/
    	
    	/*ArrayList 넣을 계획이고 넣는 타입들은 HashMap 이며
    	  HashMap은 key는 String갑, value 값은 int 값이다*/
    	List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
    	System.out.println("입력한 텍스트:"+tempStr);
    	
    	for(int i = 0 ; i < tempStr.length();i++)//입력문자의 길이만큼 i++
        {
            Map<String, Integer> map = new HashMap<String, Integer>();
            
            char test = tempStr.charAt(i);
            
            /*유니코드의 한글은 0xAC00 부터 시작*/      
            if(test >= 0xAC00)
            {
                char uniVal = (char) (test - 0xAC00);
 
                char cho = (char) (((uniVal - (uniVal % 28))/28)/21);
                char jun = (char) (((uniVal - (uniVal % 28))/28)%21);
                char jon = (char) (uniVal %28);
 
                System.out.println();
                //System.out.println(test);
                
//                System.out.printf(""+ CHO[cho]+"// 0x"
//                                            + Integer.toHexString((char) cho) );
                
                System.out.printf(""+CHO[cho]+"");
                
//                System.out.printf(""+ JUN[jun]+"// 0x"
//                                             + Integer.toHexString((char) jun) );
                
                System.out.printf(""+ JUN[jun]+"");
                                
//                if((char)jon != 0x0000)
//                    System.out.println(""+ JON[jon]+"// 0x"
//                                                 + Integer.toHexString((char) jon) );
                
                if((char)jon != 0x0000)
                    System.out.printf(""+ JON[jon]+"");
 
 
                map.put("cho", (int) cho);
                map.put("jun", (int) jun);
                map.put("jon", (int) jon);
                list.add(map);
            }
        }
 
        for(int i = 0; i < list.size() ; i++)
        {
            int a = (int)(list.get(i)).get("cho");
            int b = (int)(list.get(i)).get("jun");
            int c = (int)(list.get(i)).get("jon");
            
            /*한글 음절의 갯수는 (초성 19)*(중성 21)*(종성 28)=11,172 이다*/
            char temp = (char)(0xAC00 + 28 * 21 *(a) + 28 * (b) + (c) );
 
            lastStr = lastStr.concat(String.valueOf(temp));            
           //System.out.println(""+ (char)(0xAC00 + 28 * 21 *(a) + 28 * (b) + (c) )); -->음절 분리 확인용
        }
    	
    }
}
