import { user } from "../hooks.server";

export const prerender = true;

export const load = async (event) => {
    if (user == undefined) {
        (window as Window).location = window.location.host + '/main/landing';
        return;
    }
    
    const session = await event.locals.getSession();
    return {
        session
    };
};