package com.eltendawy.mycalculator;
import java.util.ArrayList;

public class EqulOpreation
{

   public EqulOpreation(String op)
   {

   }

   private static double calculate(String problem) throws Exception
   {
     ArrayList<String> elements =new ArrayList<String>();
     String temp="";
     int current=0;

       for(int i=0;i<problem.length();i++)
       {//3+4
           temp=problem.substring(current,i);
           if(problem.toCharArray()[i]=='+'||
              problem.toCharArray()[i]=='-'||
              problem.toCharArray()[i]=='x'||
              problem.toCharArray()[i]=='^'||
               problem.toCharArray()[i]=='%'||
               problem.toCharArray()[i]=='√'||
              problem.toCharArray()[i]=='/')
           {
               elements.add(temp);
               elements.add(problem.substring(i,i+1));
               current=i+1;
           }

       }
       elements.add(problem.substring(current,problem.length()));
       double result=0;
      for(int i=0;i<elements.size();i++)
      {
         if(elements.get(i).equals(""))
         {
             elements.remove(i);
         }
      }
      for(int i=elements.size()-1;i>-1;i--)
      {try
      {
         if(elements.get(i).contains("-"))
         {
             if(elements.get(i+1).contains("√"))
             {
                 result=Math.sqrt(Double.parseDouble(elements.get(i+2)));
                 elements.set(i+1,String.valueOf(result*(-1)));
                 elements.remove(i);
                 elements.remove(i+1);
             }
             else {
                elements.set(i+1,String.valueOf(Double.parseDouble(elements.get(i+1))*(-1)));
             elements.remove(i);
            }
         }}
      catch (Exception ex)
      {
          if(elements.get(i+1).equals("+"))
          {
              elements.remove(i+1);
              i++;
          }

      }
      }
       for(int i=0;i<elements.size();i++)
       {
           if(elements.get(i).equals("%"))
           {  if(Double.parseDouble(elements.get(i+1))<=0)// for my mine exception
              throw new Exception("minecan not find mod because of zero!");

               result= Double.parseDouble(elements.get(i-1))%Double.parseDouble(elements.get(i+1));
               elements.set(i-1,String.valueOf(result));
               elements.remove(i);
               elements.remove(i);
               i=i-1;
           }
       }
       for(int i=0;i<elements.size();i++)
       {
           if(elements.get(i).equals("^"))
           {
               result=Math.pow(Double.parseDouble(elements.get(i-1)),Double.parseDouble(elements.get(i+1)));
               elements.set(i-1,String.valueOf(result));
               elements.remove(i);
               elements.remove(i);
               i=i-1;
           }
       }
      for(int i=0;i<elements.size();i++)
      {
          if(elements.get(i).equals("√"))
          {
              if(Double.parseDouble(elements.get(i+1))<0)
                  throw new Exception("minecan not find square root for negative number!");
              result=Math.sqrt(Double.parseDouble(elements.get(i+1)));
              elements.set(i+1,String.valueOf(result));
              elements.remove(i);
              i=i-1;
          }
      }
       for(int i=0;i<elements.size();i++)
       {
           if(elements.get(i).equals("x"))
           {
               result=Double.parseDouble(elements.get(i-1))*Double.parseDouble(elements.get(i+1));
               elements.set(i-1,String.valueOf(result));
               elements.remove(i);
               elements.remove(i);
               i=i-1;
           }
           else if(elements.get(i).equals("/"))
           {
              if(Double.parseDouble(elements.get(i+1))==0)
                  throw new Exception("minecan not divide by zero!");
               result=Double.parseDouble(elements.get(i-1))/Double.parseDouble(elements.get(i+1));
               elements.set(i-1,String.valueOf(result));
               elements.remove(i);
               elements.remove(i);
               i=i-1;
           }

       }
       for(int i=0;i<elements.size();i++)
       {
           if(elements.get(i).equals("+"))
           {
               result=Double.parseDouble(elements.get(i-1))+Double.parseDouble(elements.get(i+1));
               elements.set(i-1,String.valueOf(result));
               elements.remove(i);
               elements.remove(i);
               i=i-1;
           }

       }
       for(int i=1;i<elements.size();i++)
       {
           result=Double.parseDouble(elements.get(i-1))+Double.parseDouble(elements.get(i));
           elements.set(i-1,String.valueOf(result));
           elements.remove(i);
           i=i-1;

       }
       return Double.parseDouble(String.valueOf(elements.get(0)));

   }


   private static String bracketshandler(String exprsseion,ArrayList<String> operations) {

       if(exprsseion.toCharArray()[exprsseion.length()-1]=='x')
           exprsseion=exprsseion.substring(0, exprsseion.length()-1);
       exprsseion="("+exprsseion+")";
       

     for(int i=0;i<exprsseion.length();i++)
     {
         if(!exprsseion.contains("("))
             return exprsseion;
         if(exprsseion.toCharArray()[i]=='(')
         {
             for(int j=i+1;j<exprsseion.length();j++)
             {
                 if(exprsseion.toCharArray()[j]=='(')
                     {
                     break;
                     }
                 if(exprsseion.toCharArray()[j]==')')
                 {
                     String operation=exprsseion.substring(i+1,j);
                     operations.add(operation);
                     exprsseion=exprsseion.replace(exprsseion.substring(i,j+1),"op"+(operations.size()-1));
                     break;
                 }
             }
         }
         if(i==exprsseion.length()-1)
             i=-1;
     }
     return exprsseion;
 }

   private  static String replacelog(String exprsseion) throws Exception
   {
       int position=exprsseion.indexOf("log");
       int end=0;
       int skip=0;
       double result=0;
       if(exprsseion.contains("log("))
       {

           for(int i=position+4;i<exprsseion.length();i++)
             {
                 if(exprsseion.toCharArray()[i]==')'&&skip==0)
                 {
                     end=i;break;
                 }
                 if(exprsseion.toCharArray()[i]=='(')
                       skip++;
                 if(exprsseion.toCharArray()[i]==')')
                       skip--;
             }
           String temp=exprsseion.substring(position+4,end);
           result=getresult(temp);
           if(result==0)
               throw new Exception("minecan not find log for zero!");
           if(result<0)
               throw new Exception("minecan not find log for negative number!");
            result=Math.log10(result);
           exprsseion=exprsseion.replace(exprsseion.substring(position, end+1),String.valueOf(result));
       }
     return exprsseion;

   }

   public static double getresult(String exprsseion) throws Exception {
       int countleft=0,countright=0;
       for(int i=0;i<exprsseion.length();i++)
           if(exprsseion.toCharArray()[i]=='(')
               countleft++;
           else
               if(exprsseion.toCharArray()[i]==')')
               countright++;
       while (countleft>countright)
      {
          exprsseion=exprsseion+")";
          countright++;
      }
       exprsseion= exprsseion.replace("(","x(");
       exprsseion= exprsseion.replace("√","x√");
       exprsseion= exprsseion.replace("l","xl");
       exprsseion= exprsseion.replace(")",")x");
       exprsseion= exprsseion.replace("/x","x");
       exprsseion= exprsseion.replace("+x","+");
       exprsseion= exprsseion.replace("-x","-");
       exprsseion= exprsseion.replace("xx","x");
       exprsseion= exprsseion.replace("%x","%");
       exprsseion= exprsseion.replace("gx","g");
       exprsseion= exprsseion.replace("^x","^");
       exprsseion= exprsseion.replace("√x","√");
       exprsseion=exprsseion.replace("x/","x");
       exprsseion=exprsseion.replace("x+","+");
       exprsseion=exprsseion.replace("x-","-");
       exprsseion=exprsseion.replace("xx","x");
       exprsseion=exprsseion.replace("x%","%");
       exprsseion=exprsseion.replace("x^","^");
       exprsseion=exprsseion.replace("x)",")");
       if(exprsseion.toCharArray()[exprsseion.length()-1]=='x')
           exprsseion=exprsseion.substring(0, exprsseion.length()-1);
       if(exprsseion.toCharArray()[0]=='x')
           exprsseion=exprsseion.substring(1, exprsseion.length());
       while(exprsseion.contains("+-")||exprsseion.contains("-+")||exprsseion.contains("--")||exprsseion.contains("++"))
       {
       exprsseion=exprsseion.replace("--","+");
       exprsseion=exprsseion.replace("++","+");
       exprsseion=exprsseion.replace("-+","-");
       exprsseion=exprsseion.replace("+-","-");
       exprsseion=exprsseion.replace("//","/");
       }
       while(exprsseion.contains("log("))
           exprsseion=replacelog(exprsseion);

       ArrayList<String> operations=new ArrayList<String>();
       exprsseion=bracketshandler(exprsseion,operations);
       for(int i=0;i<operations.size();i++)
       {
           operations.set(i , String.valueOf(calculate(operations.get(i))));
           for(int j=i+1;j<operations.size();j++)
           {
               operations.set(j,operations.get(j).replace("op"+i,operations.get(i)));
           }
       }

     return  Double.parseDouble(operations.get(operations.size()-1));
 }

}
