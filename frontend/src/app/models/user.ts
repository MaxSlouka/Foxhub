import {Technology} from "./technology";
import {Language} from "./language";
import {Role} from "./role";
import {Personality} from "./personality";
import {ColorPersonality} from "./colorPersonality";
import {Location} from "./location";

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
  colorPersonality?: ColorPersonality,
  locations?: Location[],
  dateOfRegistration?: Date,
  profileUrl?: string,
  profilePictureUrl?: string,
  facebook?: string,
  instagram?: string,
  linkedin?: string,
  gitHub?: string,
  workStatus?: boolean;
  optionalPage?: string,
  oneLineAbout?: string,
  workPreference?: string,
  roles?: Role[],
  technologies?: Technology[],
  languages?: Language[],
  role?: Role,
  verified?: boolean,
  inCart?: boolean,
  outOfFilters?: string[],
}
