<script>
    import {signIn, signOut} from '@auth/sveltekit/client';
    import {page} from "$app/stores";
    import Modal from "./userModal.svelte";

    let showModal = false;
</script>

{#if !$page.data.session}
    <button on:click={() => signIn(
  'auth0', {
    redirect: false,
    callbackUrl: '/'
  },
  {
    scope: 'api openid profile email'
  }
)} class="authbutton">Sign in
    </button>
{:else}
    <button id="user-icon" on:click={() => (showModal = true)}>
        <img src={$page.data.session.user.image} alt="Avatar" class="avatar"/>
    </button>
    <Modal bind:showModal>
        {#if $page.data?.session?.user}
            <h3>Signed in as {$page.data.session.user.name}</h3>
            {#if $page.data.session.user.email}
                <small>{$page.data.session.user.email}</small>
            {/if}
            <a href="/settings">Settings</a>
            <button on:click={() => signOut('auth0', {
  redirect: true,
  callbackUrl: '/logout'
})} class="authbutton">Sign out
            </button>
        {/if}
    </Modal>
{/if}

<style>
    #user-icon {
        padding: 0;
        background-color: #ccc;
        border: none;
        border-radius: 100%;
        height: 3rem;
        aspect-ratio: 1;
        overflow: clip;
    }
    small, button {
        display: block;
        color: inherit;
    }

    .authbutton {
        border: 2px solid var(--accent-color);
        border-radius: 10px;
        font-size: 1.1em;
        padding: 0.7rem 1rem;
        background: transparent;
        margin: 1rem 0;
    }

    a {
        display: block;
        text-decoration: none;
        margin: 1rem 0;
    }

    .avatar {
        height: 100%;
        width: 100%;
    }
</style>
