import { redirect } from '@sveltejs/kit';

export const load = async (event) => {
    const session = await event.locals.getSession();
    console.log(event)

    if (session && event.url.pathname == '/') {
        throw redirect(307, '/main/landing');
    }

    return {
        session
    };
};
