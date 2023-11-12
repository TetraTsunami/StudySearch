import { createAuth0Client, User } from '@auth0/auth0-spa-js';
import { AUTH0_DOMAIN, AUTH0_CLIENTID } from "$env/static/private"
import { onMount } from 'svelte';
import { page } from '$app/stores';

export const auth0 = await createAuth0Client({
    domain: AUTH0_DOMAIN,
    clientId: AUTH0_CLIENTID,
    cacheLocation: 'localstorage'
});

export async function login() {
    await auth0.loginWithRedirect({
        authorizationParams: {
            redirect_uri: $page.url.pathname
        }
    })
}

export async function logout() {
    await auth0.logout()
}

export let user: User | undefined;
let url: string;

onMount(async () => {
    url = window.location.href;
    await auth0.handleRedirectCallback()
        .then(async () => {
            user = await auth0.getUser();
        })
        .catch(() => {
            (window as Window).location = window.location.host;
        });
})