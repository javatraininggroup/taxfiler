package com.company.taxfiler;

import java.util.HashSet;
import java.util.Set;

public class CollectionRetainAndRemove {

	private Set<String> roles;

	public void retainTest(Set<String> newRoles) {
		//this.getRoles().retainAll(newRoles);
		newRoles.retainAll(newRoles);
		//System.out.println(this.getRoles());
		System.out.println(newRoles);
	}

	public void removeTest(Set<String> newRoles) {
		this.getRoles().remove(newRoles);
		System.out.println(this.getRoles());
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public static void main(String a[]) {
		CollectionRetainAndRemove obj = new CollectionRetainAndRemove();
		Set<String> newRoles = new HashSet<>();
		newRoles.add("Member");
		obj.setRoles(newRoles);

		Set<String> toBeUpdatedRoles = new HashSet<>();
		toBeUpdatedRoles.add("Member");
		toBeUpdatedRoles.add("online");

		//obj.removeTest(toBeUpdatedRoles);
		obj.retainTest(toBeUpdatedRoles);
	}

}
