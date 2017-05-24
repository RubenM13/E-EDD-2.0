package es.ucm.fdi.edd.core.erlang.model;

public class MFA {

	private String module;
	private String function;
	private Long arity;

	public MFA() {
		// TODO Auto-generated constructor stub
	}
	
	public MFA(String module, String function, Long arity) {
		this.module = module;
		this.function = function;
		this.arity = arity;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Long getArity() {
		return arity;
	}

	public void setArity(Long arity) {
		this.arity = arity;
	}
}
