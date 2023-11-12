<script lang="ts">
    import Dialog from "../dialog.svelte";
    let ppl_min_val: string = "0";
    let ppl_max_val: string = "10"
    let ppl_max_min: string = "1";
    let ppl_max_max: string = "10";
    function ppl_min_update() {
        let minimum: number = Number.parseInt(ppl_min_val);
        ppl_max_min = (minimum + 1).toString();
        ppl_max_max = Math.max(minimum + 1, 10).toString();
    }
</script>

<div class="centered-container">
    <h1 class="page-title"><a class="same-unvisited" href="/settings">Settings</a> / General</h1>
</div>
<br/>

<div class="hv-centered-container settings-form">
    <form method="post" action="/api/user/settings/general">
        <label for="name">Enter your name:</label>
        <input type="text" id="name" name="name" placeholder="John Doe" required>
        <br/>
        <label for="phone">Enter your phone number (for notifications, optional):</label>
        <input type="tel" id="phone" placeholder="123-456-678" name="phone">
        <br/>
        <label for="email">Enter your email (for notifications, optional):</label>
        <input type="email" id="email" placeholder="jdoe@example.com" name="email">
        <br/>
        <label for="pss">Do you prefer to be in groups where you're all from the same section?</label>
        <input type="checkbox" checked name="pss">
        <br/>
        <label for="desiredPeopleMin">Minimum number of people you prefer:</label>
        <input type="range" step="1" min="0" max="10" bind:value="{ppl_min_val}" name="desiredPeopleMin" on:input={ppl_min_update}>
        <output>{ppl_min_val}</output>
        <br/>
        <label for="desiredPeopleMax">Maximum number of people you prefer:</label>
        <input type="range" step="1" min="{ppl_max_min}" max="{ppl_max_max}" bind:value="{ppl_max_val}" name="desiredPeopleMax">
        <output>{ppl_max_val}</output>
        <br/>
        <input type="hidden" name="redirect" required value="/settings/gen">
        <button type="submit">Submit</button>
    </form>
</div>
<Dialog />

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

    .settings-form {
        border: inset 2px;
        padding: 20px;
        font-size: 18pt;
    }

    a.same-unvisited {
        color:blue;
        text-decoration: none;
    }

    a.same-unvisited:visited {
        color: blue;
    }

    br {
        margin: 15px;
    }
</style>
