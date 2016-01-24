package com.fdmgroup.commands;

public class ActivityInvoker
{
	private ICommand command;

	public ActivityInvoker()
	{

	}

	public ActivityInvoker(ICommand command)
	{
		this.command = command;
	}

	public void setCommand(ICommand command) {
		this.command = command;
	}

	public void invoke()
	{
		command.execute();
	}
}
