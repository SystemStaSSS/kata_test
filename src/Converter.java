import java.util.HashMap;
import java.util.Map;

 class Converter {
    private String[] roman1 = {" ", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private String[] roman10 = {" ", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    private Map<Integer, String> arabian = new HashMap<>();
    private Map<String, Integer> roma = new HashMap<>();

    public Converter(){
        for (int i = 0; i < roman10.length; i++) {
            String s = roman10[i];
            roma.put(s, i * 10);
            arabian.put(i * 10, s);
            for (int j = 0; j < roman1.length; j++) {
                String t = s + roman1[j];
                t = t.trim();
                if (!t.isBlank()) {
                    roma.put(t, i * 10 + j);
                    arabian.put(i * 10 + j, t);

                }
            }
        }
    }
    public String getRoma(Integer arab){
        return arabian.get(arab);
    }
    public Integer getArab(String rom){
        return  roma.get(rom);
    }
}
