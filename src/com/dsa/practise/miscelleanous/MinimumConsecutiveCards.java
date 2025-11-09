package com.dsa.practise.miscelleanous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MinimumConsecutiveCards {
    public static void main(String[] args) {
        int[] cards = new int[] {1, 2, 3, 1, 2, 1};
        System.out.println("the minimum no of cards to be picked is : " + minimumCardPickup(cards));
    }
    public static int minimumCardPickup(int[] cards) {
        int diff = Integer.MAX_VALUE;
        List<Integer> indexList = new LinkedList<>();
        Map<Integer, LinkedList<Integer>> numDiffMap = new HashMap<>();
        for(int i=0; i<cards.length; i++){
            if(!numDiffMap.containsKey(cards[i])){
                LinkedList<Integer> inList = new LinkedList<>();
                inList.add(i);
                numDiffMap.put(cards[i], inList);
            }else{
                List<Integer> containedList = numDiffMap.get(cards[i]);
                containedList.add(i);
            }
        }

        for(Map.Entry<Integer, LinkedList<Integer>> e : numDiffMap.entrySet()){
            System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue());

        }

        List<List<Integer>> listOfIndexLists = new ArrayList<>(numDiffMap.values());

        List<List<Integer>> finalList = new ArrayList<>();
        for(List l : listOfIndexLists){
            if(l.size()>=2){
                finalList.add(l);
            }
        }

        System.out.println("List with more than 2 elements: " );
        for(List<Integer> l : finalList){
            System.out.println(l);
        }

        if(finalList.isEmpty()){
            return -1;
        }

        for(List<Integer> l : finalList){
            for(int j=1; j<l.size(); j++){
                diff = Math.min(diff, l.get(j) - l.get(j-1));
            }
        }

        return diff+1;
    }
}
