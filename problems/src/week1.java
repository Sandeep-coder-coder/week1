import java.util.*;
public class week1 {
    static HashMap<String,Integer> pageViews=new HashMap<>();
    static HashMap<String,Set<String>> uniqueVisitors=new HashMap<>();
    static HashMap<String,Integer> trafficSources=new HashMap<>();
    public static void processEvent(String url,String userId,String source){
        pageViews.put(url,pageViews.getOrDefault(url,0)+1);
        uniqueVisitors.putIfAbsent(url,new HashSet<>());
        uniqueVisitors.get(url).add(userId);
        trafficSources.put(source,trafficSources.getOrDefault(source,0)+1);
    }
    public static void getDashboard(){
        List<Map.Entry<String,Integer>> pages=new ArrayList<>(pageViews.entrySet());
        pages.sort((a,b)->b.getValue()-a.getValue());
        System.out.println("Top Pages:");
        for(int i=0;i<Math.min(10,pages.size());i++){
            String url=pages.get(i).getKey();
            int views=pages.get(i).getValue();
            int unique=uniqueVisitors.get(url).size();
            System.out.println((i+1)+". "+url+" - "+views+" views ("+unique+" unique)");
        }
        System.out.println("Traffic Sources:");
        int total=0;
        for(int c:trafficSources.values())total+=c;
        for(String s:trafficSources.keySet()){
            double percent=(trafficSources.get(s)*100.0)/total;
            System.out.println(s+": "+percent+"%");
        }
    }
    public static void main(String[] args){
        processEvent("/article/breaking-news","user123","google");
        processEvent("/article/breaking-news","user456","facebook");
        processEvent("/sports/championship","user789","direct");
        processEvent("/sports/championship","user123","google");
        processEvent("/article/breaking-news","user999","google");
        getDashboard();
    }
}