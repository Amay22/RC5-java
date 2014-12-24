import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyExp {
    String s[] = new String[26];
    public static void main(String[] args) throws Exception{
        KeyExp k = new KeyExp();
        k.compute();
        for (int i = 0; i < 26; i++) {  
            System.out.println(k.s[i]);
        }
        
    }
    public String[] compute() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter uKey = ");
        String ukey = fullfill0(br.readLine());
        //String s[] = new String[26];
        String l[] = new String[4];
        s[0] = Long.toBinaryString(Long.parseLong("B7E15163", 16));//(Pw)
        for (int i = 1; i < s.length; i++) {
            s[i] = add(s[i - 1], fullfill0(Long.toBinaryString(Long.parseLong("9E3779B9", 16))));//S[i] = S[i-1]+ 0x9E3779B9 (Qw)
        }
        for (int i = 0; i< l.length ; i++) {
            l[i] = (Long.toBinaryString(Long.parseLong(ukey.substring((3-i) * 8, (((3-i) + 1) * 8)), 16)));//S[i] = S[i-1]+ 0x9E3779B9 (Qw)
        }
        for (int i = 0; i< l.length ; i++) {
            System.out.println("l = "+(Long.toHexString(Long.parseLong(l[i],2))));
        }
        
        int i = 0, j = 0;
        String a = "", b = "",temp = "";
        for (int count = 0; count < 78; count++) {                                      
            //A = S[i] = (S[i] + A + B) <<< 3;
            temp = add(fullfill0(s[i]) ,add(fullfill0(a), fullfill0(b)));
            a = s[i] = temp.substring(3)+temp.substring(0,3);
            //B = L[j] = (L[j] + A + B) <<< (A + B);
            temp = add(fullfill0(l[j]) ,add(fullfill0(a), fullfill0(b)));
            long le  = (Long.parseLong(add(fullfill0(a), fullfill0(b)),2))%32;
            int len  = Integer.parseInt(""+le);
            b = l[j] = temp.substring(len)+temp.substring(0,len);
            i = (i + 1) % 26;
            j = (j + 1) % 4;
        }
        return s;
    }
    public String fullfill0(String x) {
        return (get0(32 - x.length()) + x);
    }

    public String get0(int len) {
        String result = "";
        for (int i = 0; i < len; i++) {
            result += "0";
        }
        return result;
    }

    public String add(String x, String y) {
        String result = "";
        boolean carry = false;
        for (int i = x.length()-1; i >= 0; i--) {
            if ((x.charAt(i) == y.charAt(i) && carry == false) || (x.charAt(i) != y.charAt(i) && carry == true)) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            if ((x.charAt(i) == '1' && y.charAt(i) == '1')
                    || (x.charAt(i) == '1' && y.charAt(i) == '1' && carry == true)
                    || (x.charAt(i) != y.charAt(i) && carry == true)) {
                carry = true;
            } else {
                carry = false;
            }
        }
        return result;
    }

}
/*00000000000000001208224912082249*/
