package org.nlt.util;

import org.nlt.model.Students;

public class StudentsUtil {
	public static boolean validate(Students students)
	{
		if(students.getName().isEmpty())
		{
			return true;
		}
		return false;
	}
}
