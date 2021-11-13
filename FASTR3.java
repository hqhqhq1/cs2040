import java.util.*;

public class FASTR3 {
    public static void main(String[] args){
        Scanner Scan = new Scanner(System.in);

        int numberofrunners = Scan.nextInt();
        Scan.nextLine();

        HashMap<String,Double> runnerFirstTime = new HashMap<String,Double>();//Link runnerID and first lap time
        HashMap<Double,String> SecondTimerunner = new HashMap<Double,String>();//Link runner second lap time with runnerID

        String [] runnernamesinarray;//Initialize array with runner names
        runnernamesinarray = new String [numberofrunners];//Size of array with runner names

        Double [] ArrayofSecondLapTimes;//Initialize an array with second lap times
        ArrayofSecondLapTimes = new Double[numberofrunners];

        LinkedList<String> CurrentWinningTeam = new LinkedList<>();//Current Winning Team Stored here

        Double BestTime = 0.0;//Time of CurrentWinningTeam stored here

        //Storing of data phase-Done       
        for(int i = 0; i < numberofrunners;i++){
            String runnerID=Scan.next();
            Double runnerFirstLap=Scan.nextDouble();
            Double runnerSecondLap=Scan.nextDouble();
            runnerFirstTime.put(runnerID, runnerFirstLap);//Runner name link to first laptime
            SecondTimerunner.put(runnerSecondLap,runnerID);//Second laptime link to runner name         
            runnernamesinarray[i]=runnerID;//Array with all the runners
            ArrayofSecondLapTimes[i]=runnerSecondLap;//Array with all the runners second laptime
        }

       // Arrays.sort(ArrayofSecondLapTimes);//Sort array of second lap times

        Double [] Top4timingsOfSecondRun;//Array of Top 4 timings,Cannot use 3 as it might be a duplicate
        Top4timingsOfSecondRun = new Double [4];
        for(int j = 0; j < 4 ;j++){
            Top4timingsOfSecondRun[j] = ArrayofSecondLapTimes[j];
        }

        for(int i = 0; i < numberofrunners; i++){//Set the team up around the first runner
            //First runner info
            String CurrentFirstRunner=runnernamesinarray[i];//First runner id
            double CurrentFirstRunnerTime=runnerFirstTime.get(CurrentFirstRunner);//First runner first lap time           

            //Current teams total time
            Double Totaltime=CurrentFirstRunnerTime;

            LinkedList<String> CurrentTeam = new LinkedList<>();
            CurrentTeam.add(CurrentFirstRunner);//Add First runner

            //Selection of runner 2,3,4
            for(int k = 0; k < 4; k++){
                if(SecondTimerunner.get(Top4timingsOfSecondRun[k])==CurrentFirstRunner){
                    //Do Nothing as name is duplicate
                }else if(CurrentTeam.size()==4){
                    break;//Max team Size Hit to prevent Extra
                }else{
                    CurrentTeam.add(SecondTimerunner.get(Top4timingsOfSecondRun[k]));
                    Totaltime=Totaltime+Top4timingsOfSecondRun[k];//If name not duplicated:Add time to total time
                }
            }


            //Compare timing to see if it is the best time     
            if(BestTime==0.0){
                BestTime=Totaltime;//For the start
                CurrentWinningTeam=CurrentTeam;
            }else if(BestTime<=Totaltime){
                //DontDoAnything
            }else{
                //Total Time is better than best time:New best time
                CurrentWinningTeam=CurrentTeam;
                BestTime=Totaltime;
            }
        }
        //Print out all the names in the list
        System.out.println(BestTime);
        System.out.println(CurrentWinningTeam.get(0));
        System.out.println(CurrentWinningTeam.get(1));
        System.out.println(CurrentWinningTeam.get(2));
        System.out.println(CurrentWinningTeam.get(3));

    }//Main method
}//Class


//Done By Darren Tng(A0227251M)