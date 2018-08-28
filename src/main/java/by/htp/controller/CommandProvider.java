package by.htp.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.controller.command.Command;
import by.htp.controller.command_name.CommandName;
import by.htp.controller.impl.AboutUs;
import by.htp.controller.impl.AcceptVehicle;
import by.htp.controller.impl.AddNews;
import by.htp.controller.impl.AddVehicle;
import by.htp.controller.impl.BanUser;
import by.htp.controller.impl.DeleteVehicle;
import by.htp.controller.impl.DeleteVehicleAdmin;
import by.htp.controller.impl.DetailVehicle;
import by.htp.controller.impl.EditUser;
import by.htp.controller.impl.Filtrate;
import by.htp.controller.impl.GetAllUsers;
import by.htp.controller.impl.Localization;
import by.htp.controller.impl.MainPage;
import by.htp.controller.impl.SalesPage;
import by.htp.controller.impl.SignIn;
import by.htp.controller.impl.SignOut;
import by.htp.controller.impl.SignUp;
import by.htp.controller.impl.ToAddNewsPage;
import by.htp.controller.impl.ToAddVehiclePage;
import by.htp.controller.impl.ToProfilePage;
import by.htp.controller.impl.ToProfilePageAdmin;
import by.htp.controller.impl.ToSignPage;
import by.htp.controller.impl.ToSignUpPage;

public class CommandProvider {
	
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
	

	public CommandProvider() {
		commands.put(CommandName.MAIN_PAGE, new MainPage());
		commands.put(CommandName.TO_SIGN_IN_PAGE, new ToSignPage());
		commands.put(CommandName.TO_SIGN_UP_PAGE, new ToSignUpPage());
		commands.put(CommandName.TO_ADD_VEHICLE_PAGE, new ToAddVehiclePage());
		commands.put(CommandName.TO_ADD_NEWS_PAGE, new ToAddNewsPage());
		commands.put(CommandName.TO_PROFILE_PAGE, new ToProfilePage());
		commands.put(CommandName.TO_PROFILE_PAGE_ADMIN, new ToProfilePageAdmin());
		
		commands.put(CommandName.LOCALIZATION, new Localization());
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_UP, new SignUp());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.DELETE_VEHICLE, new DeleteVehicle());
		commands.put(CommandName.EDIT_USER, new EditUser());
		
		commands.put(CommandName.SALES_PAGE, new SalesPage());
		commands.put(CommandName.ABOUT_US, new AboutUs());
		commands.put(CommandName.ADD_VEHICLE, new AddVehicle());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.DETAIL_VEHICLE, new DetailVehicle());
		commands.put(CommandName.ACCEPT_VEHICLE, new AcceptVehicle());
		commands.put(CommandName.DELETE_VEHICLE_ADMIN, new DeleteVehicleAdmin());
		commands.put(CommandName.GET_ALL_USERS, new GetAllUsers());
		commands.put(CommandName.BAN_USER, new BanUser());
		commands.put(CommandName.FILTRATE, new Filtrate());
		
	}
	
	
	
	
	public Command getCommand(String commandName) {
		Command command;
		command = commands.get(CommandName.valueOf(commandName.toUpperCase()));
		return command;
		
		
	}
	

}
