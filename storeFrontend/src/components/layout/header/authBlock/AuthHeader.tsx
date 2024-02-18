import { cookies } from "next/headers";
import AuthCredencial from "./AuthCredencial";
import UserAvatar from "./UserAvatar";
import { getCurrentUser } from "@/api/user/user.api";
import { UserAuthenticate } from "@/types/users/userInfo.type";

const AuthHeader = async () => {
    try {
        const auth = await getCurrentUser({
            headers: {
                Cookie: cookies().toString(),
            },
        });
        return <UserAvatar auth={auth as UserAuthenticate} />;
    } catch (error) {
        return <AuthCredencial />;
    }
};

export default AuthHeader;
