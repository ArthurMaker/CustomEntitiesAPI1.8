package me.dablakbandit.customentitiesapi.entities;

import ja.ClassClassPath;
import ja.CtClass;
import ja.CtField;
import ja.CtNewMethod;
import me.dablakbandit.customentitiesapi.CustomEntitiesAPI;
import me.dablakbandit.customentitiesapi.NMSUtils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class CustomEntityBlaze extends CustomEntityMonster {

	public CustomEntityBlaze() {
		super("CustomEntityBlaze");
		if (ctClass == null)
			return;
		register();
	}

	public CustomEntityBlaze(Location location) {
		this();
		a();
		spawnEntity(location);
	}

	public CustomEntityBlaze(Entity e) {
		this();
		a();
		try {
			entity = customentity.cast(NMSUtils.getHandle(e));
		} catch (Exception e1) {
		}
	}

	public CustomEntityBlaze(Object o) {
		this();
		a();
		entity = o;
	}

	public static Class<?> getCustomEntityBlazeClass() {
		try {
			return Class.forName("temp.CustomEntityBlaze");
		} catch (Exception e) {
			return null;
		}
	}

	public void a() {
		try {
			customentity = Class.forName("temp.CustomEntityBlaze");
			helper = Class.forName("temp.CustomEntityBlazeHelper");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void register() {
		try {
			customentity = Class.forName("temp.CustomEntityBlaze");
		} catch (Exception e1) {
			try {
				cp.insertClassPath(new ClassClassPath(
						new CustomEntityBlazeHelper().getClass()));
				CtClass ces = cp
						.getAndRename(
								"me.dablakbandit.customentitiesapi.entities.CustomEntityBlazeHelper",
								"temp.CustomEntityBlazeHelper");
				ces.setSuperclass(cp.get("temp.CustomEntityMonsterHelper"));
				ces.toClass();
				CtClass EntityBlaze = cp.getCtClass("net.minecraft.server."
						+ NMSUtils.getVersion() + "EntityBlaze");
				cp.importPackage("net.minecraft.server."
						+ NMSUtils.getVersion() + "EntityBlaze");
				cp.importPackage("net.minecraft.server."
						+ NMSUtils.getVersion() + "EntityMonster");
				for (CtField f : fields) {
					ctClass.addField(f);
				}
				fields.clear();
				ctClass.setSuperclass(EntityBlaze);
				methods.add("public void setUnableToMove(){"
						+ "CustomEntityBlazeHelper.setUnableToMove(this);"
						+ "}");
				methods.add("public void setAbleToMove(){"
						+ "CustomEntityBlazeHelper.setAbleToMove(this);" + "}");
				methods.add("public void setAbleToMove(double d){"
						+ "CustomEntityBlazeHelper.setAbleToMove(this, d);"
						+ "}");
				for (String m : methods) {
					ctClass.addMethod(CtNewMethod.make(m, ctClass));
				}
				methods.clear();
				customentity = ctClass.toClass();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (customentity != null)
			CustomEntitiesAPI.getInstance().registerEntity("EntityBlaze", 61,
					customentity);
	}

	public void setGoalSelectorDefaultPathfinderGoals() {
		try {
			helper.getMethod("setGoalSelectorDefaultPathfinderGoals",
					Object.class).invoke(null, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newGoalSelectorPathfinderGoalBlazeFireballDefault() {
		try {
			helper.getMethod("newGoalSelectorPathfinderBlazeFireball", Object.class)
					.invoke(null, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeGoalSelectorPathfinderGoalBlazeFireball() {
		try {
			helper.getMethod("removeGoalSelectorPathfinderBlazeFireball",
					Object.class).invoke(null, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newGoalSelectorPathfinderGoalMoveTowardsRestrictionDefault() {
		newGoalSelectorPathfinderGoalMoveTowardsRestriction(1.0D);
	}
	
	public void newGoalSelectorPathfinderGoalRandomStrollDefault(){
		newGoalSelectorPathfinderGoalRandomStroll(1.0D);
	}
	
	public void newGoalSelectorPathfinderGoalLookAtPlayerDefault(){
		newGoalSelectorPathfinderGoalLookAtPlayer(EntityName.ENTITYHUMAN, 8.0F);
	}
}
