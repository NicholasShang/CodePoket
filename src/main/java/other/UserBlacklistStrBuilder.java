package other;

import java.io.*;

/**
 * Created by Nick on 2018/4/26.
 */
public class UserBlacklistStrBuilder {

    public String build(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("BL1(3000).txt");
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append("{\"username\":\"");
                result.append(s);
                result.append("\"},");
            }
            result.deleteCharAt(result.length()-1);//去掉最后的【，】
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
//        System.out.println(UserBlacklistStrBuilder.class.getClassLoader().getResource("").getPath());
        UserBlacklistStrBuilder  userBlacklistStrBuilder = new UserBlacklistStrBuilder();
        userBlacklistStrBuilder.build();
    }

}
