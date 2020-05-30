import java.text.*;
import java.util.Arrays;
import java.io.*;
import java.nio.file.*;
public class dist {
    public static void main(String[] args) throws Exception{
        String input = "";
        File f = new File(System.getProperty("user.dir")+"\\input.txt");
        File res = new File(System.getProperty("user.dir")+"\\res.txt");
        try{
        res.createNewFile();
       input = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));}
        catch(Exception e){}
        int index = 0;
        String current = "";
        int numOfElements = 0;
        int currentElement = 0;
        int XorY = 0;
        input = input.replace(System.getProperty("line.separator"), "W");
        for(int y =0; y < input.length();y++){
            if(input.substring(y,y+1).intern()=="W"){
                numOfElements++;
            }
        }
        String[][] pos = new String[numOfElements+1][2];
        for(int x = 0; x<pos.length;x++){
            for(int d = 0; d< 2;d++){
                pos[x][d]="";
            }
        }
        System.out.println(input);
        while(true){
            try{
            current = input.substring(index,index+1);}
            catch(Exception e){
                break;
            }
            if(current.intern() == "W"){
                currentElement++;
                XorY = 0;
                index+=1;
                System.out.println(currentElement);
                continue;
            }
            if(current.intern() == " "){
                XorY =1;
                index++;
                continue;
            }  
            pos[currentElement][XorY] += current;
            index++;
        }
        DecimalFormat df = new DecimalFormat("0.000");
        String[] ans= new String[numOfElements];
        for(int M = 0; M<numOfElements; M++){
            if(M == numOfElements-1){
                ans[M]="0.000";
            }
            else{
                int[] initarr = {Integer.parseInt(pos[M][0]),Integer.parseInt(pos[M][1])};
                int[] resarr = {Integer.parseInt(pos[M+1][0]),Integer.parseInt(pos[M+1][1])};
                ans[M]=df.format(calcdist(initarr,resarr));
            }
        }
        FileWriter fw = new FileWriter(res);
        for(int W=0; W<ans.length;W++){
            fw.append(ans[W]+System.getProperty("line.separator"));
        }
        fw.flush();
        fw.close();
    }
    public static double calcdist(int[] initPos, int[] resPos){
        double ans = 0;
        //input checking
        if(initPos.length != 2 || resPos.length!=2){
            throw new IllegalArgumentException("cant have a single number as pos in 2D");
        }
        for (int i = 0; i < 2; i++){
            if(initPos[i] < 0){
                throw new IllegalArgumentException("cant have negative pos");
            }
            if (resPos[i] < 0){
                throw new IllegalArgumentException("cant have negative pos");
            }
        }
        ans = poth(Math.abs(initPos[0]-resPos[0]),Math.abs(initPos[1]-resPos[1]));
        return ans;
    }
    public static double poth(int x, int y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
}
