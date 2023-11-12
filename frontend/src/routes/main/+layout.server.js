import { login, user } from '../../hooks.server.js';

export const load = async (event) => {
    if (user == undefined) {
        login();
        return;
    }

    const session = await event.locals.getSession();
    return {
        session
    };
};
