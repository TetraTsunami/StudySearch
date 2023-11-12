<script>
    export let showModal;
    let dialog;

    $: if (dialog && showModal) dialog.showModal();
</script>

<!-- svelte-ignore a11y-click-events-have-key-events a11y-no-noninteractive-element-interactions -->
<dialog
        bind:this={dialog}
        on:close={() => (showModal = false)}
        on:click|self={() => dialog.close()}
>
    <!-- svelte-ignore a11y-no-static-element-interactions -->
    <div on:click|stopPropagation>
        <slot />
    </div>
</dialog>

<style>
    dialog {
        max-width: 32em;
        border-radius: 10px;
        border: none;
        padding: 0;
        position: fixed;
        top: 3.5rem;
        right: 1rem;
        left: unset;
    }
    dialog::backdrop {
        background: transparent;
    }
    dialog > div {
        padding: 1em;
    }
    dialog[open] {
        animation: drop 0.1s ease-out;
    }
    @keyframes drop {
        from {
            opacity: 0;
            transform: scale(0.9);
        }

        to {
            opacity: 1;
            transform: scale(1);
        }
    }
    button {
        display: block;
    }
</style>
