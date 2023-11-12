<script>
    import { onMount } from "svelte";

    let missing = [];
    onMount(async () => {
        let fetched_missing_data = await fetch("/api/user/seings/missing");
        missing = await fetched_missing_data.json();
    });
    let settings_pages;
    $: settings_pages = [
        {
            "name": "General",
            "relurl": "/gen",
            "missing": missing.includes("profile"),
        },
        {
            "name": "Classes",
            "relurl": "/cls",
            "missing": missing.includes("classes"),
        },
        {
            "name": "Availability",
            "relurl": "/ava",
            "missing": missing.includes("availability"),
        },
    ];
</script>

<div class="centered-container">
    <h1 class="page-title">Settings</h1>
</div>
<br/>
<div class="settings-pages-buttons">
    <div class="hv-centered-container">
        {#each settings_pages as page}
            <div class="centered-container">
                <button class="settings-page" on:click={() => {
                    document.location += page.relurl;
                }}>
                    {page.name} {#if page.missing}⚠️{/if}
                </button>
            </div>
            <br/>
        {/each}
    </div>
</div>

<div class="at-bottom">
    <p>The ⚠️ icon indicates that there is currently missing information in the given category.</p>
</div>

<style>
    .centered-container {
        display: flex;
        justify-content: center;
    }

    .hv-centered-container {
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    .page-title {
        font-size: 40pt;
    }
    
    .settings-page {
        font-size: 30pt;
        margin-top: 10px;
    }

    .at-bottom {
        position: absolute;
        bottom: 10px;
    }
</style>
