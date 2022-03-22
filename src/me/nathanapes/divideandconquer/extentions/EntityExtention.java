package me.nathanapes.divideandconquer.extentions;

import net.minecraft.server.v1_8_R1.EntityArmorStand;
import net.minecraft.server.v1_8_R1.World;

public class EntityExtention extends EntityArmorStand{

	public EntityExtention(World world) {
		super(world);
	}
	
	public int SoldierP1Xloc;
	public int SoldierP1Yloc;
	public int SoldierP1Zloc;
	
	public int SoldierP2Xloc;
	public int SoldierP2Yloc;
	public int SoldierP2Zloc;
	
	public int SoldierP3Xloc;
	public int SoldierP3Yloc;
	public int SoldierP3Zloc;
	
	public void getArmorStandloc(){
		
		if(this.getCustomName().equalsIgnoreCase("P1's Soldier")){
			
			SoldierP1Xloc = this.getChunkCoordinates().getX();
			
			SoldierP1Yloc = this.getChunkCoordinates().getY();
			
			SoldierP1Zloc = this.getChunkCoordinates().getZ();
			
		}
		
		if(this.getCustomName().equalsIgnoreCase("P2's Soldier")){
			
			SoldierP2Xloc = this.getChunkCoordinates().getX();
			
			SoldierP2Yloc = this.getChunkCoordinates().getY();
			
			SoldierP2Zloc = this.getChunkCoordinates().getZ();
			
		}
		
		if(this.getCustomName().equalsIgnoreCase("P3's Soldier")){
			
			SoldierP3Xloc = this.getChunkCoordinates().getX();
			
			SoldierP3Yloc = this.getChunkCoordinates().getY();
			
			SoldierP3Zloc = this.getChunkCoordinates().getZ();
			
		}
		
	}
	
}
