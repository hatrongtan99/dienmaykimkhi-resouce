export interface AddressUserResponse {
    id: number;
    userId: number;
    fullName: string;
    phoneNumber: string;
    addressLine1: string;
    addressLine2: string;
    addressLine3: string;
    isDefault: boolean;
}

export interface AddressSaveOrUpdateDto {
    fullName: string;
    phoneNumber: string;
    addressLine1: string;
    addressLine2: string;
    addressLine3: string;
    isDefault: boolean;
}
