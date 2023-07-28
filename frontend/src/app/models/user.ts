import { Technology } from "./technology";
import { Language } from "./language";
import {Role} from "./role";
import {Personality} from "./personality";

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
  personality?: Personality,
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
  workStatus?:boolean;
  optionalPage?: string,
  roles?: Role[],
  technologies?: Technology[],
  languages?: Language[],
  role?: Role,
  verified?: boolean,
  inCart?: boolean,
}
