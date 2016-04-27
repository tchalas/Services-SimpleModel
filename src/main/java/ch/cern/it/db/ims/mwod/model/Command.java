package ch.cern.it.db.ims.mwod.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMMAND")
public class Command {

	private Long command_id;
	private String name;
	private String parameters;
	private List<Action> actions = new ArrayList<Action>();

	public Command() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getCommand_id() {
		return command_id;
	}

	public void setCommand_id(Long command_id) {
		this.command_id = command_id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARAMETERS")
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "commands")
	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

}
