import {Technology} from "./technology";
import {Language} from "./laguage";


export interface User {

  id?: number;
  firstName: string,
  lastName: string,
  nickname?: string,
  email: string,
  phone?: string,
  about?: string,
  yearOfBirth?: number,
  password: string,
  personality?: string,
  location?: string,
  dateOfRegistration?: Date,
  profileUrl?: string,
  profilePictureUrl?: string,
  completeProjects?: string,
  yearsOfExperience?: string,
  facebook?: string,
  instagram?: string,
  linkedin?: string,
  gitHub?: string,
  optionalPageURL?: string,
  roles?: string[],
  technologies?: Technology[],
  languages?: Language[],
}
