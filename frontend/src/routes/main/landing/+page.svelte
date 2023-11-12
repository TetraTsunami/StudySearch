<script lang="ts">
    type Group = {
        "id": number,
        "short_members": string,
        "pros": string[],
        "cons": string[]
    };
    type MoreMarker = {
        "special": "more"
    }
    let groups_by_class: Record<string, (Group | MoreMarker)[]> = {
        "COMP SCI 300": [
            {
                "id": 111,
                "short_members": "Joe, Bob, and Your Mom",
                "pros": ["Same Section"],
                "cons": []
            },
            {
                "id": 222,
                "short_members": "Alan, Alan and Alan",
                "pros": [],
                "cons": []
            },
            {
                "id": 333,
                "short_members": "Wallace and Grommit",
                "pros": [],
                "cons": ["May Be Too Small"]
            },
            {
                "special": "more",
            }
        ],
        "MATH 234": [],
        "ASTRON 200": [
            {
                "id": 101,
                "short_members": "Neil and Carl",
                "pros": ["Same Section"],
                "cons": ["May Be Too Small"]
            }
        ],
    };
</script>

<div class="centered-container">
    <h1 class="page-title">Welcome</h1>
    <br/>
</div>
<div>
    <hr/>
    {#each Object.entries(groups_by_class) as [name, groups]}
        <h2>For <i>{name}</i></h2>
        <div class="groups-for-a-class">
            {#each groups as obj}
                {#if "special" in obj}
                    <div class="group-listing group-listing-more">Load more...</div>
                {:else}
                    {#each [obj] as {id, short_members, pros, cons}}
                        <div class="group-listing group-listing-nonempty">
                            <div class="group-listing-content">
                                <strong class="group-members">{short_members}</strong>
                                <div class="pro-con-listing">
                                    {#each pros as pro}<div class="pro-con pro">{pro}</div>{/each}{#each cons as con}<div class="pro-con con">{con}</div>{/each}
                                </div>
                            </div>
                        </div>
                    {/each}
                {/if}
            {:else}
                <div class="group-listing group-listing-empty">
                    <p>No groups available, please check back later. 😢</p>
                </div>
            {/each}
        </div>
        <hr/>
    {/each}
</div>



<style>
    .centered-container {
        display: flex;
        justify-content: center;
    }

    .page-title {
        font-size: 40pt;
    }

    .groups-for-a-class {
        display: flex;
        justify-content: center;
    }

    .group-listing {
        display: inline-block;
        margin: 10px;
        padding: 5px;
        border-radius: 6px;
    }

    .group-listing-content {
        display: grid;
    }

    .group-members {
        font-size: 20pt;
    }

    .pro-con-listing {
        display: inline-block;
    }

    .pro-con {
        border-radius: 3px;
        width: max-content;
        height: max-content;
        padding: 3px;
        margin: 10px;
    }

    .pro {
        background-color: lime;
    }

    .con {
        background-color: red;
    }

    .group-listing-nonempty {
        background-color: lightskyblue;
    }

    .group-listing-empty {
        background-color: lightgrey;
    }

    .group-listing-more {
        background-color: lightgrey;
        font-size: 20pt;
    }
</style>
