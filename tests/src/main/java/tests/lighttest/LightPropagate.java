package tests.lighttest;

import jpize.Jpize;
import jpize.math.Maths;
import jpize.math.vecmath.vector.Vec2i;
import jpize.sdl.input.Btn;
import jpize.sdl.input.Key;
import jpize.sdl.input.KeyAction;

import java.util.ArrayDeque;
import java.util.Queue;

/** Реализация */
public class LightPropagate extends LightPropagateFrame{

    /** 4 направления (для перебора в цикле) */
    public static final Vec2i[] normals = {new Vec2i(-1, 0), new Vec2i(0, 1), new Vec2i(1, 0), new Vec2i(0, -1)};

    /** Очереди для добавления и удаления света во избежание рекурсии */
    Queue<LightNode> increaseQueue = new ArrayDeque<>();
    Queue<LightNode> decreaseQueue = new ArrayDeque<>();

    /** Добавить в очередь */
    public void addToIncreaseQueue(int i, int j, int level){
        if(level != 0)
            set(i, j, level);

        if(level != 1){
            LightNode node = new LightNode(i, j, level);
            if(increaseQueue.contains(node))
                return;

            increaseQueue.add(node);
        }
    }

    private void addToDecreaseQueue(int i, int j, int level){
        if(level == 0)
            return;

        LightNode node = new LightNode(i, j, level);
        if(decreaseQueue.contains(node))
            return;

        set(i, j, 0);
        decreaseQueue.add(node);
    }

    public void addToDecreaseQueue(int i, int j){
        int level = get(i, j);
        addToDecreaseQueue(i, j, level);
    }

    public void processIncrease(){
        while(!increaseQueue.isEmpty())
            nextIncrease();
    }

    public void processDecrease(){
        while(!decreaseQueue.isEmpty())
            nextDecrease();
        processIncrease();
    }


    public void nextIncrease(){
        LightNode node = increaseQueue.poll();
        assert node != null;

        int setNeighborLevel = node.level - 1;
        if(setNeighborLevel == 0)
            return;

        for(Vec2i normal: normals){
            int nx = node.x + normal.x;
            int ny = node.y + normal.y;

            if(nx < 0 || ny < 0 || nx >= WIDTH || ny >= HEIGHT)
                continue;

            int nLevel = get(nx, ny);
            if(setNeighborLevel > nLevel)
                addToIncreaseQueue(nx, ny, setNeighborLevel);
        }
    }

    public void nextDecrease(){
        LightNode node = decreaseQueue.poll();
        assert node != null;

        int expectedNeighborLevel = node.level - 1;
        if(expectedNeighborLevel == -1)
            return;

        for(Vec2i normal: normals){
            int nx = node.x + normal.x;
            int ny = node.y + normal.y;

            if(nx < 0 || ny < 0 || nx >= WIDTH || ny >= HEIGHT)
                continue;

            int nLevel = get(nx, ny);
            if(nLevel == expectedNeighborLevel)
                addToDecreaseQueue(nx, ny, expectedNeighborLevel);
            else if(nLevel >= expectedNeighborLevel)
                addToIncreaseQueue(nx, ny, nLevel);
        }
    }



    /** Добавление / Удаление света */
    @Override
    public void handleTouch(int i, int j, KeyAction action){
        if(Btn.RIGHT.isDown()){
            int level = Maths.random(3, 11);
            addToIncreaseQueue(i, j, level);
            //processIncrease();
        }
        if(Btn.LEFT.isDown()){
            addToDecreaseQueue(i, j);
            //processDecrease();
        }
    }

    /** Поэтапная отработка света на клавиши 'N' и 'D' */
    public LightPropagate(){
        Jpize.context().callbacks().addKeyCallback(((key, keyAction, keyMods) -> {
            if(keyAction == KeyAction.UP)
                return;
            if(key == Key.N)
                if(!increaseQueue.isEmpty())
                    nextIncrease();
            if(key == Key.D)
                if(!decreaseQueue.isEmpty())
                    nextDecrease();
        }));
    }

    /** Визуализация очередей при поэтапной отработке света */
    @Override
    public void render(){
        super.render();

        batch.begin();
        for(LightNode node: increaseQueue){
            int i = node.x;
            int j = node.y;
            float x = beginX + i * tileSizeW;
            float y = beginY + j * tileSizeH;
            batch.drawRect(0.29, 1.00, 0.27, 0.1, x, y, tileSizeW, tileSizeH);
        }
        for(LightNode node: decreaseQueue){
            int i = node.x;
            int j = node.y;
            float x = beginX + i * tileSizeW;
            float y = beginY + j * tileSizeH;
            batch.drawRect(1.00, 0.27, 0.30, 0.1, x, y, tileSizeW, tileSizeH);
        }
        batch.end();
    }

}
