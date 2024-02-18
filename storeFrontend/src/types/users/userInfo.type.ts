export interface UserAuthenticate {
    fullName: string;
    email: string;
    username: string;
}

export interface UserInfoResponse {
    id: number;
    userId: number;
    avatarUrl: string;
    fullName: string;
    email: string;
    numberPhone: string;
    isActive: boolean;
}

export interface UserInfoUpdate {
    avatarUrl: string | null;
    fullName: string;
    numberPhone: string | null;
}
