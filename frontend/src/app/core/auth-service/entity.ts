export interface User {
  id: number
  email: string
  name: string
}

export interface LoginDto {
  email: string
  password: string
}

export interface RegisterDto {
  name: string
  email: string
  password: string
}

export interface LoginResponse {
  token: string
  user: User
}

export interface RefreshResponse {
  token: string
  user?: User
}

export enum Roles {
  ADMIN = "ADMIN",
  USER = "USER"
}
