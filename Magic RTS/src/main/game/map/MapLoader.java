package main.game.map;

import java.io.*;
import java.util.ArrayList;

import org.newdawn.slick.geom.Point;


public class MapLoader {

	public static Map loadMap(String file) {
		int step = 0;
		int playerCount;
		int height;
		int width;
		boolean[] flags = new boolean[8];
		String title = "";
		Point[] spawnLocations;
		
		ArrayList<String> outputData = new ArrayList<String>();
		ArrayList<Integer> byteData = new ArrayList<Integer>();
		
		try {
			//open file
			InputStream inputStream = new FileInputStream(file + ".map");
			int byteRead;
			
			//read data
            while ((byteRead = inputStream.read()) != -1) {
                byteData.add(byteRead);
            }
       
			inputStream.close();
			
			//set mapid
			
			step++;
			//set title
			for (int i = 0; i < 31; i++) {
				title += String.valueOf(Character.toChars(byteData.get(i+1)));
				step++;
			}
			title = title.trim();
			
			//set playercount
			playerCount = byteData.get(step);
			spawnLocations = new Point[playerCount];
			step++;
			
			//get height
			height = (byteData.get(step) & 0xFF) << 8 | (byteData.get(step+1) & 0xFF);
			step += 2;
			
			//get width
			width = (byteData.get(step) & 0xFF) << 8 | (byteData.get(step+1) & 0xFF);
			step += 2;
			
			//get flags
			char[] flagData = Integer.toBinaryString(byteData.get(step)).toCharArray();
			
			for(int i = 0; i < flagData.length; i++) {
				if (flagData[i] == '1') {
					flags[i] = true;
				} else {
					flags[i] = false;
				}
			
			}
			
			step++;
			//get start location
			for (int i = 0; i < 8; i++) {
				if (i < playerCount) {
					int x_ = (byteData.get(step) & 0xFF) << 8 | (byteData.get(step+1) & 0xFF);
					int y_ = (byteData.get(step+2) & 0xFF) << 8 | (byteData.get(step+3) & 0xFF);
					
					spawnLocations[i] = new Point(x_,y_);
				}
				step += 4;
				
			}
			//put tiles in a list
			ArrayList<Integer> mapList = new ArrayList<Integer>();
			
			while (step < byteData.size()-1) {
				for (int i = 0; i < byteData.get(step+1); i++) {
					mapList.add(byteData.get(step));
				}
				
				step+=2;
			}
			//break list into a string
			step = 0;
			String tempString;
			for (int y_ = 0; y_ < height; y_++) {
				tempString = "";
				for (int x_ = 0; x_ < width; x_++) {
					tempString += " " + Integer.toString(mapList.get(step)-1);
					step++;
				}
				outputData.add(tempString.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map m = new Map(title, "grass", outputData);
		
		return m;
	}
}


