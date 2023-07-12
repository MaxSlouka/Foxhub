import {Technology} from "./Technology";

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
  socialMedias?: string[];
  technologies?: Technology[];
  languages?: string[];
}
