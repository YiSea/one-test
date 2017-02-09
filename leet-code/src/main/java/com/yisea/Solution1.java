package com.yisea;

/**
 * 
  * @Package com.leetcode 
  * @ClassName Solution.java
  * @author jun.wu  
  * @date 2017年2月9日 上午9:57:09 
  * @Description: 1. Two Sum
 */
public class Solution1 {
	/*
	 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	
		You may assume that each input would have exactly one solution, and you may not use the same element twice.
	
		Example:
		Given nums = [2, 7, 11, 15], target = 9,
	
		Because nums[0] + nums[1] = 2 + 7 = 9,
		return [0, 1].
	*/
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i<nums.length; i++){
            int numA = nums[i];
            int numB = target - numA;
            for(int j = 0; j<nums.length; j++){
                if(i == j){
                    continue;
                }
                int numC = nums[j];
                if(numC == numB){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
