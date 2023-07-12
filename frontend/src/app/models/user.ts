import {Technology} from "./technology";
import {SocialMedia} from "./socialMedia";
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
  roles?: string[];
  socialMedias?: SocialMedia[];
  technologies?: Technology[];
  languages?: Language[];
}
