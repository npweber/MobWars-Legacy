package me.nathanapes.divideandconquer;

import me.nathanapes.divideandconquer.extentions.EntityExtention;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin implements Listener{
	
	
	public FileConfiguration config;
	
	public EntityExtention entityextention;
	
	public boolean start;
	
	public boolean hasstarted;
	
	public boolean hassetup;
	
	public boolean hasrun;
	
	public boolean shouldheal;
	
	public boolean shouldcounter;
	
	public boolean alreadyjoined;
	
	public boolean shouldloop;

	public int countdown;
	
	public int lobbycount;
	
	public int aicount;
	
	public int broadcast;
	
	public int joiners;
	
	public int cpus;
	
	public int losters;
	
	public int mobsdeadP1;
	
	public int mobsdeadP2;
	
	public int mobsdeadP3;
	
	public int randomnum;
	
	public int timesrun;
	
	public int P1armyhealth;
	
	public int P2armyhealth;
	
	public int P3armyhealth;
	
	public int[] locsoldiersP1;
	
	public int[] locsoldiersP2;
	
	public int[] locsoldiersP3;
	
	public int[] P1armyspawnloc;
	
	public int[] P2armyspawnloc;
	
	public int[] P3armyspawnloc;
	
	public int[] Playertploc;
	
	public String joiner;
	
	public String P1;
	
	public String P2;
	
	public String P3;
	
	public String lost1;
	
	public String lost2;
	
	public String winner;
	
	public String[] losers;
	
	public int nummobsP1 = 0;
	
	public int nummobsP2 = 0;
	
	public int nummobsP3 = 0;
	
	public int numlost;
	
	public final BukkitScheduler scheduler = this.getServer().getScheduler();
	
	public double XP1;
	public double YP1;
	public double ZP1;
	 
	public double XP2;
	public double YP2;
	public double ZP2;
	 
	public double XP3;
	public double YP3;
	public double ZP3;
	 
	public double XPl;
	public double YPl;
	public double ZPl;
	
	public String w;
	
	public void onEnable(){
		
		config = getConfig();
		
		config.createSection("ArmySpawnLocationP1XCord");
		config.createSection("ArmySpawnLocationP1YCord");
		config.createSection("ArmySpawnLocationP1ZCord");
		 
		config.createSection("ArmySpawnLocationP2XCord");
		config.createSection("ArmySpawnLocationP2YCord");
		config.createSection("ArmySpawnLocationP2ZCord");
		 
		config.createSection("ArmySpawnLocationP3XCord");
		config.createSection("ArmySpawnLocationP3YCord");
		config.createSection("ArmySpawnLocationP3ZCord");
		 
		config.createSection("PlayerTPLocationXCord");
		config.createSection("PlayerTPLocationYCord");
		config.createSection("PlayerTPLocationZCord");
		
		saveDefaultConfig();
		
		entityextention = new EntityExtention(null);
		
		start = false;
		
		hasstarted = false;
		
		hasrun = true;
		
		alreadyjoined = false;
		
		hassetup = false;
		
		shouldloop = false;
		
		countdown = 5;
		
		joiners = 0;
		
		losters = 0;
		
		randomnum = 1;
		
		timesrun = 0;
		
		P1armyhealth = 20;
		
		P2armyhealth = 20;
		
		P3armyhealth = 20;
		
		mobsdeadP1 = 0;
		
		mobsdeadP2 = 0;
		
		mobsdeadP3 = 0;
		
		P1armyspawnloc = (int[]) new int[] {(int) XP1, (int) YP1, (int) ZP1};
		
		P2armyspawnloc = (int[]) new int[] {(int) XP2, (int) YP2, (int) ZP2};
		
		P3armyspawnloc = (int[]) new int[] {(int) XP3, (int) YP3, (int) ZP3};
		
		Playertploc = (int[]) new int[] {(int) XPl, (int) YPl, (int) ZPl};
		
		locsoldiersP1 = new int[] {entityextention.SoldierP1Xloc, entityextention.SoldierP1Yloc, entityextention.SoldierP1Zloc};
		
		locsoldiersP2 = new int[] {entityextention.SoldierP2Xloc, entityextention.SoldierP2Yloc, entityextention.SoldierP2Zloc};
		
		locsoldiersP3 = new int[] {entityextention.SoldierP3Xloc, entityextention.SoldierP3Yloc, entityextention.SoldierP3Zloc};
		
		XP1 = config.getDouble("ArmySpawnLocationP1XCord");
		YP1 = config.getDouble("ArmySpawnLocationP1YCord");
		ZP1 = config.getDouble("ArmySpawnLocationP1ZCord");
		 
		XP2 = config.getDouble("ArmySpawnLocationP2XCord");
		YP2 = config.getDouble("ArmySpawnLocationP2YCord");
		ZP2 = config.getDouble("ArmySpawnLocationP2ZCord");
		 
		XP3 = config.getDouble("ArmySpawnLocationP3XCord");
		YP3 = config.getDouble("ArmySpawnLocationP3YCord");
		ZP3 = config.getDouble("ArmySpawnLocationP3ZCord");
		 
		XPl = config.getDouble("PlayerTPLocationXCord");
		YPl = config.getDouble("PlayerTPLocationYCord");
		ZPl = config.getDouble("PlayerTPLocationZCord");
		
		w = config.getString("World");
		
		this.getServer().getPluginManager().registerEvents(this, this);
		
		this.getLogger().info("Enabling!");
		
	}
	
	public void onDisable(){
		this.getLogger().info("Disabling!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("startgamecycle")) { 

					start = true;
					
					if(start == true && hasstarted == false){
						
						setup();
						
						hassetup = true;
						
					}
					
					if(start == true && 
							//(joiners == 3 ||
							joiners == 1 && cpus == 2 && hassetup == true){
						
						start();
						
						hasstarted = true;
						
					}
					
					if(hasstarted == true && start == true && (losters == 2 || cpus == 0)){
						
						takedown();
					
						hasstarted = false;
						
					}
					
					shouldloop = true;
						
					if(start == true && shouldloop == true){
					
						ifnomorearmy();
					
						ifthereiswinner();
						
						count();
						
						countdeadsoldiers();
						
						displayhealth();
						
						if(cpus == 0){
						
							addcpus();
							
						}
						
						cpuai();
			
					}
			
		}
		
		else if (cmd.getName().equalsIgnoreCase("stopgamecycle")) { 
			
		start = false;
		
		}
	
	return true; 
	
	}
	
	public void setup(){
		
		if(nummobsP1 < 30){
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon ArmorStand 653 4 -637 {Equipment:[{id:267}, {id:298, tag:{display:{color:1}}}, {id:299, tag:{display:{color:1}}}, {id:300, tag:{display:{color:1}}}, {id:301, tag:{display:{color:1}}}], NoBasePlate:1, CustomName:P1Soldier20HP, CustomNameVisible:1}");
			nummobsP1 += 1;
					
		}
				
		if(nummobsP2 < 30){
				
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon ArmorStand 617 4 -671 {Equipment:[{id:267}, {id:298, tag:{display:{color:4}}}, {id:299, tag:{display:{color:4}}}, {id:300, tag:{display:{color:4}}}, {id:301, tag:{display:{color:4}}}], NoBasePlate:1, CustomName:P2Soldier20HP, CustomNameVisible:1}");
			nummobsP2 += 1;
					
		}
				
		if(nummobsP3 < 30){
				
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "summon ArmorStand 587 4 -699 {Equipment:[{id:267}, {id:298, tag:{display:{color:14}}}, {id:299, tag:{display:{color:14}}}, {id:300, tag:{display:{color:14}}}, {id:301, tag:{display:{color:14}}}], NoBasePlate:1, CustomName:P3Soldier20HP, CustomNameVisible:1}");
			nummobsP3 += 1;
					
		}
		
		hassetup = true;
		
	}
	
	public void start(){
		
		scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
		
			public void run(){
			
				if(countdown > 0){
					
					broadcast = Bukkit.broadcastMessage(ChatColor.GREEN + "Divide & Conquer is starting in " + ChatColor.GREEN + countdown);
					countdown -= 1;
				
				}
			
			}
		
		}, 0L, 20L);
		
		scheduler.scheduleSyncDelayedTask(this, new Runnable(){
			
				public void run(){
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P1 + " 591 33 -646");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P2 + " 591 33 -646");	
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P3 + " 591 33 -646");
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 1 " + P1);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 1 " + P2);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamemode 1 " + P3);
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + P1 + " minecraft:iron_sword 1");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + P2 + " minecraft:iron_sword 1");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + P3 + " minecraft:iron_sword 1");
				
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + P1 + " minecraft:redstone_block 1");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + P2 + " minecraft:redstone_block 1");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + P3 + " minecraft:redstone_block 1");
				
			}
			
		}, 95L);
		
	}
	
	public void takedown(){
		
		Bukkit.broadcastMessage(winner + " has WON Divide & Conquer!");
		
		scheduler.scheduleSyncDelayedTask(this, new Runnable(){
			
			public void run(){
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + P1);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + P2);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + P3);
				
			}
			
		}, 300L);
		
	}
	
	public void ifnomorearmy(){
		
		if(mobsdeadP1 == 29){
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill " + P1);
			
		}
		
		if(mobsdeadP2 == 29){
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill " + P2);
			
			if(cpus == 2 || cpus == 1){
				
				cpus -= 1;
				
			}
			
		}
		
		if(mobsdeadP3 == 29){
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill " + P3);
				
			if(cpus == 2 || cpus == 1){
				
				cpus -= 1;
				
			}
			
		}
		
		if(cpus == 0){
			
			winner = P1;
			
		}
		
	}
	
	public void ifthereiswinner(){
		
		if(losters == 2){
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=ArmorStand, c=29]");
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=ArmorStand, c=29]");
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=ArmorStand, c=29]");
			
		}
		
	}
	
	public void countdeadsoldiers(){
		
		if(Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "testfor @e[type=ArmorStand, name=P1Soldier0HP]") == true){
		
			mobsdeadP1 += 1;
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=ArmorStand, name=P1Soldier0HP]");
			
		}
		
		if(Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "testfor @e[type=ArmorStand, name=P2Soldier0HP]") == true){
			
			mobsdeadP2 += 1;
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=ArmorStand, name=P2Soldier0HP]");
			
		}
		
		if(Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "testfor @e[type=ArmorStand, name=P3Soldier0HP]") == true){
			
			mobsdeadP3 += 1;
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=ArmorStand, name=P3Soldier0HP]");
			
		}
		
	}
	@EventHandler
	public void playersdie(PlayerRespawnEvent pre){
		
		if(!(pre.getPlayer().getName().equalsIgnoreCase("nathanapes"))){
			
			if(pre.getPlayer().getDisplayName().equalsIgnoreCase(P1)){
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P1 + " ~0 ~20 ~0");
				
				pre.getPlayer().hidePlayer(pre.getPlayer());
				
			}
			if(pre.getPlayer().getDisplayName().equalsIgnoreCase(P2)){
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P2 + " ~0 ~20 ~0");
				
				pre.getPlayer().hidePlayer(pre.getPlayer());
				
			}
			if(pre.getPlayer().getDisplayName().equalsIgnoreCase(P3)){
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P3 + " ~0 ~20 ~0");
				
				pre.getPlayer().hidePlayer(pre.getPlayer());
				
			}
			
				losters += 1;
				
				if(losters == 2){
					if(pre.getPlayer().getDisplayName().equalsIgnoreCase(P1)){
					
					winner = P1;
						
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P1 + " ~0 ~20 ~0");
					
					}
				
					if(pre.getPlayer().getDisplayName().equalsIgnoreCase(P2)){
					
					winner = P2;
						
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P2 + " ~0 ~20 ~0");
					
					}
					
					if(pre.getPlayer().getDisplayName().equalsIgnoreCase(P3)){
				
					winner = P3;	
						
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P3 + " ~0 ~20 ~0");
					
					}
				}
		}
	}
	@EventHandler
	public void playerjoingame(PlayerInteractEvent pie){
		if(pie.getAction().equals(Action.RIGHT_CLICK_BLOCK) && pie.getClickedBlock().getLocation().equals(new Location(Bukkit.getWorld(w),(double) 661,(double) 4,(double) -605))){
			if(joiners < 4){
				
				if(!(pie.getPlayer().getDisplayName().equalsIgnoreCase(P1) || pie.getPlayer().getDisplayName().equalsIgnoreCase(P2) || pie.getPlayer().getDisplayName().equalsIgnoreCase(P3))){
					
					if(joiners == 0 && alreadyjoined == false){
					
						P1 = pie.getPlayer().getName();
					
						joiners +=1;
					
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P1 + " ~0 ~20 ~0");
					
						alreadyjoined = true;
				
					}
					if(joiners == 1 && alreadyjoined == false){
					
						P2 = pie.getPlayer().getName();
					
						joiners +=1;
					
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P2 + " ~0 ~20 ~0");
					
						alreadyjoined = true;
				
					}
					if(joiners == 2 && alreadyjoined == false){
					
						P3 = pie.getPlayer().getName();
					
						joiners +=1;
					
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + P3 + " ~0 ~20 ~0");
					
						alreadyjoined = true;
				
					}
					pie.getPlayer().sendMessage("You joined a game!");	
				}
				
				else{
					
					pie.getPlayer().sendMessage("Nathan has already tried.");
					
				}
				
				alreadyjoined = false;
				
			}
			
			else{
				
				pie.getPlayer().sendMessage("This game is full! So no, you cannot join." + ChatColor.RED);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void playerunleasharmyandattack(PlayerInteractEvent pie){
		
		if(pie.getItem().equals(Material.IRON_SWORD) && pie.getAction().equals(Action.LEFT_CLICK_AIR)){
			
			if(pie.getPlayer().getDisplayName().equalsIgnoreCase(P1)){
			
				randomnum();
				
				if(randomnum == 1){
						
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P1s Soldiers " + P1armyhealth + "HP, c=29] " + locsoldiersP2);
					
					scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
						
						public void run(){
							
							P1armyhealth -= 1;
							
							P2armyhealth -= 1;
							
						}
						
					}, 35L, 10L);
					
				}
				
				if(randomnum == 2){
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P1s Soldier " + P1armyhealth + "HP, c=29] " + locsoldiersP3);

					scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
						
						public void run(){
							
							P1armyhealth -= 1;
							
							P3armyhealth -= 1;
							
						}
						
					}, 35L, 10L);
					
				}
				
			}
			
			if(pie.getPlayer().getDisplayName().equalsIgnoreCase(P2)){
				
				randomnum();
				
				if(randomnum == 1){
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P2s Soldier " + P2armyhealth + "HP, c=29] " + locsoldiersP1);
					
					scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
						
						public void run(){
							
							P2armyhealth -= 1;
							
							P1armyhealth -= 1;
							
						}
						
					}, 35L, 10L);
					
				}
				
				if(randomnum == 2){
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P2s Soldier " + P2armyhealth + "HP, c=29] " + locsoldiersP3);
					
					scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
						
						public void run(){
							
							P2armyhealth -= 1;
							
							P3armyhealth -= 1;
							
						}
						
					}, 35L, 10L);
					
				}
				
			}
			
			if(pie.getPlayer().getDisplayName().equalsIgnoreCase(P3)){
				
				randomnum();
				
				if(randomnum == 1){
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P3Soldier" + P3armyhealth + "HP, c=29] " + locsoldiersP1);
					
					scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
						
						public void run(){
							
							P3armyhealth -= 1;
							
							P1armyhealth -= 1;
							
						}
						
					}, 35L, 10L);
					
				}
				
				if(randomnum == 2){
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P3Soldier" + P3armyhealth + "HP, c=29] " + locsoldiersP2);

					scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
						
						public void run(){
							
							P3armyhealth -= 1;
							
							P2armyhealth -= 1;
							
						}
						
					}, 35L, 10L);
					
				}
				
			}
			
		}
		
	}
	
	public void randomnum(){
		
		if(timesrun == 2){
			
			hasrun = true;
			
			timesrun = 0;
			
		}
		
		if(hasrun == true){
		
			if(randomnum == 1){
			
				randomnum = 2;
				
			}
			
		}
		
		if(hasrun == false){
		
			if(randomnum == 2){
			
				randomnum = 1;
				
			}
			
		}
		
		hasrun = false;
		
		timesrun += 1;
		
	}
	@EventHandler
	public void healarmy(PlayerInteractEvent pie){
		
		if(pie.getAction().equals(Action.LEFT_CLICK_AIR) && pie.getItem().equals(Material.REDSTONE_BLOCK)){
		
			if(shouldheal == true){
				
				if(pie.getPlayer().getDisplayName().equalsIgnoreCase(P1)){
					
					P1armyhealth += 1;
					
					shouldheal = false;
					
					counttillheal();
					
				}
				
				if(pie.getPlayer().getDisplayName().equalsIgnoreCase(P2)){
					
					P2armyhealth += 1;
					
					shouldheal = false;
					
					counttillheal();
					
				}	
				
				if(pie.getPlayer().getDisplayName().equalsIgnoreCase(P3)){
					
					P3armyhealth += 1;
					
					shouldheal = false;
					
					counttillheal();
					
				}	
				
			}
			
		}
		
	}
	
	public void counttillheal(){
		
		BukkitScheduler scheduler = this.getServer().getScheduler();
		
		scheduler.scheduleSyncDelayedTask(this, new Runnable(){
			
			public void run(){
				
				shouldheal = true;
				
			}
			
		}, 400);
		
	}
	
	public void displayhealth(){
			
		scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
			
			public void run(){
		
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "entitydata @e[type=ArmorStand,name=P1Soldier20HP] {CustomName:P1Soldier" + P1armyhealth + "HP}");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "entitydata @e[type=ArmorStand,name=P2Soldier20HP] {CustomName:P2Soldier" + P2armyhealth + "HP}");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "entitydata @e[type=ArmorStand,name=P3Soldier20HP] {CustomName:P3Soldier" + P3armyhealth + "HP}");
			}
			
		},0L, 200L);
		
	}
	
	public void count(){
		
		if(joiners == 1 || joiners == 2 || joiners == 3){
			
			scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
				
				public void run(){
					
					lobbycount += 1;
					
				}
				
			}, 0L, 20L);
			
			scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
				
				public void run(){
					
					aicount += 1;
					
				}
				
			}, 0L, 20L);
			
		}
		
	}
	
	public void addcpus(){
		
		if(lobbycount == 10){
			
			cpus = 2;
			
		}
		
	}
	
	public void cpuai(){
		
		if(aicount >= 15 || shouldcounter == true){
			
			if(P2armyhealth < 20){
				
				P2armyhealth += 1;
				
				aicount = 0;
				
				shouldcounter = false;
				
			}
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P2's Soldier " + P2armyhealth + "HP, c=29] " + locsoldiersP1);
			
			if(P3armyhealth < 20){
				
				P3armyhealth += 1;
				
				aicount = 0;
				
				shouldcounter = false;
				
			}
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @e[type=ArmorStand, name=P3's Soldier " + P3armyhealth + "HP, c=29] " + locsoldiersP1);
			
		}
		
	}
	@EventHandler
	public void playermove(PlayerInteractEvent pie){
		
		if(pie.getAction().equals(Action.LEFT_CLICK_AIR) && (pie.getItem().equals(Material.REDSTONE_BLOCK) || pie.getItem().equals(Material.IRON_SWORD))){
			
			shouldcounter = true;
			
		}
		
	}
	
}
