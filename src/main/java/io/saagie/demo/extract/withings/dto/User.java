package io.saagie.demo.extract.withings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * Name	Description
 * id	The user identifier
 * firstname	The user's firstname
 * lastname		The user's lastname
 * shortname	The user's shortname
 * gender		The user's gender (0 for male, 1 for female)
 * birthdate	The user's birthdate in Epoch format (number of seconds since January 1st, 1970)
 *
 * @author youen
 */
@Data
@NoArgsConstructor
public class User
{
	private Long id;
	private String firstname;
	private String lastname;
	private String shortname;
	private boolean isFemale;
	private Date birthDate;
	private boolean isPublic;
}
