import {Technology} from "./technology";
import {Language} from "./laguage";



export class User {
  id?: number;
  firstName!: string;
  lastName!: string;
  nickname?: string;
  email!: string;
  telephone?: string;
  about?: string;
  yearOfBirth?: number;
  password!: string;
  personality?: string;
  countryResidence?: string;
  dateOfRegistration?: Date;
  profileUrl?: string;
  profilePictureUrl?: string;
  completeProjects?: string;
  yearsOfExperience?: string;
  facebookURL?: string;
  instagramURL?: string;
  linkedInURL?: string;
  gitHubURL?: string;
  optionalPageURL?: string;
  roles?: string[];
  technologies?: Technology[];
  languages?: Language[];
}
